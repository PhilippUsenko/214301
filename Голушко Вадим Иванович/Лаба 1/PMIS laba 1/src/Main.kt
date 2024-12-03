//4.	Определение времени года и дня недели:
// Напишите программу, которая принимает дату в формате dd.mm.yyyy
// и определяет как сезон года, так и день недели.
// Программа должна учитывать особенности високосных годов и нестандартных календарей.

//  https://lifehacker.ru/kakoj-den-nedeli/

fun main() {
    println("Введите дату в формате dd.mm.yyyy:")
    val input = readlnOrNull()
    if (input != null) {
        val day = input.substring(0, 2).toInt()
        val month = input.substring(3, 5).toInt()
        val year = input.substring(6).toInt()

        // Обычный календарь
        val leapYear = year % 4 == 0

        if (leapYear)
            println("Высокосный год")
        else println("Не высокосный год")
        val codeMonth = when (month) {
            1 -> if (leapYear) {
                0
            } else 1

            2 -> if (leapYear) {
                3
            } else 4

            3 -> 4
            4 -> 0
            5 -> 2
            6 -> 5
            7 -> 0
            8 -> 3
            9 -> 6
            10 -> 1
            11 -> 4
            12 -> 6
            else -> -1
        }
        val t = when (month) {
            1, 3, 5, 7, 8, 10 -> 31

            2 -> if (leapYear) 29
            else 28

            4, 6, 9, 11 -> 30
            else -> -1
        }

        val codeCentury = when ((year / 100) % 4) {
            0 -> 6
            1 -> 4
            2 -> 2
            3 -> 0
            else -> -1
        }
        val temp = year % 100
        val codeYear = (codeCentury + temp + temp / 4) % 7
        val dayOfTheWeak = when ((day + codeMonth + codeYear) % 7) {
            0 -> "Суббота"
            1 -> "Воскресенье"
            2 -> "Понедельник"
            3 -> "Вторник"
            4 -> "Среда"
            5 -> "Четверг"
            6 -> "Пятница"
            else -> "Invalid input"
        }
        val season = when (month) {
            1, 2, 12 -> "Зима"
            3, 4, 5 -> "Весна"
            6, 7, 8 -> "Лето"
            9, 10, 11 -> "Осень"
            else -> "Неправильный месяц"
        }
        var check = true
        if (day > t || t == -1 || month > 12) {
            check = false
            println("Такой даты не существует")
        } else println("Стандартный календарь:\nДень недели: $dayOfTheWeak\nСезон года $season")

        if (check) {
            // Календарь Майя
            var totalDays = 0
            for (y in 1..year) {
                totalDays += if (y % 4 == 0) 366
                else 365
            }

            for (m in 1..month) {
                totalDays += when (m) {
                    1, 3, 5, 7, 8, 10 -> 31

                    2 -> if (leapYear) 29
                    else 28

                    4, 6, 9, 11 -> 30
                    else -> 0
                }
            }
            totalDays += day
            val mayaDays = (totalDays + 1721425 - 584285) % 1872000
            val baktun = mayaDays / 144000
            val katun = mayaDays % 144000 / 7200
            val tun = mayaDays % 7200 / 360
            val vinal = mayaDays % 360 / 18
            val kin = mayaDays % 18
            println("\nКалендарь Майя:\n$baktun.$katun.$tun.$vinal.$kin")

            // Китайский календарь
            val cycle = year / 60
            val chineseYear = year % 60
            val heavenlyBranch = when (chineseYear % 10) {
                0 -> "Гуй"
                1 -> "Ци"
                2 -> "И"
                3 -> "Бин"
                4 -> "Дин"
                5 -> "У"
                6 -> "Цзи"
                7 -> "Гэн"
                8 -> "Синь"
                9 -> "Жэнь"
                else -> "Invalid input"
            }
            val earthBranch = when (chineseYear % 12) {
                0 -> "Хай"
                1 -> "Цзы"
                2 -> "Чоу"
                3 -> "Инь"
                4 -> "Мао"
                5 -> "Чэнь"
                6 -> "Сы"
                7 -> "У"
                8 -> "Вэй"
                9 -> "Шэнь"
                10 -> "Ю"
                11 -> "Сюй"
                else -> "Invalid input"
            }
            println("\nКитайский календарь:\nГод $heavenlyBranch-$earthBranch, $cycle год $chineseYear цикла")
        }
    } else println("Invalid input")
}