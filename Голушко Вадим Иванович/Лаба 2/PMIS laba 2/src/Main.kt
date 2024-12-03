import kotlin.random.Random

//8.	Пересечение и объединение массивов с условиями:
// Напишите программу, которая находит пересечение и объединение двух массивов чисел,
// но с учетом заданных пользователем условий (например, числа должны быть четными и больше 10).

fun main() {
    val arr1 = (IntArray(7) { Random.nextInt(1, 20) }).toSet().toIntArray()
    val arr2 = (IntArray(7) { Random.nextInt(1, 20) }).toSet().toIntArray()
    println(arr1.joinToString())
    println(arr2.joinToString())

    var filterCondition: (Int) -> Boolean = { it > 0 }

    println("Введите %, > или <, а потом число")
    val input = readlnOrNull().toString()
    val operation = input[0]
    var num = ""
    for (i in 1..<input.length)
        num += input[i]
    val number = num.toIntOrNull()
    if (number != null) {
        var result = ""
        when (operation) {
            '%' -> filterCondition = { it % number == 0 }

            '<' -> filterCondition = { it < number }

            '>' -> filterCondition = { it > number }

            else -> result = "invalid input"
        }
        if (result != "invalid input") {
            val filteredArray1 = arr1.filter(filterCondition)
            val filteredArray2 = arr2.filter(filterCondition)

            val intersection = filteredArray1.intersect(filteredArray2).toIntArray()

            val union = (filteredArray1 + filteredArray2).toSet().toIntArray()

            println("Пересечение: ${intersection.joinToString()}")
            println("Объединение: ${union.joinToString()}")
        } else
            println(result)
    } else
        println("invalid input")
}