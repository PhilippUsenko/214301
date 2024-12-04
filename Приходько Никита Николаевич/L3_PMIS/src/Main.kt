fun main() {
    println("Выберите операцию:")
    println("1. Максимум из трех чисел")
    println("2. Сумма двух чисел")
    println("3. Умножение двух чисел")

    val choice = readLine()?.toIntOrNull()

    val maxOrNull: (List<Int>) -> Int? = { numbers ->
        if (numbers.isEmpty()) null
        else {
            var max = numbers[0]
            for (num in numbers) {
                if (num > max) max = num
            }
            max
        }
    }

    val sum: (List<Int>) -> Int = { numbers ->
        var total = 0
        for (num in numbers) total += num
        total
    }

    val reduce: (List<Int>, (Int, Int) -> Int) -> Int? = { numbers, operation ->
        if (numbers.isEmpty()) null
        else {
            var result = numbers[0]
            for (i in 1 until numbers.size) {
                result = operation(result, numbers[i])
            }
            result
        }
    }

    val operation: ((List<Int>) -> Int)? = when (choice) {
        1 -> { numbers ->
            if (numbers.size == 3) maxOrNull(numbers) ?: 0 else {
                println("Введите три числа.")
                0
            }
        }
        2 -> { numbers ->
            if (numbers.size == 2) sum(numbers) else {
                println("Введите два числа.")
                0
            }
        }
        3 -> { numbers ->
            if (numbers.size == 2) reduce(numbers) { acc, i -> acc * i } ?: 0 else {
                println("Введите два числа.")
                0
            }
        }
        else -> {
            println("Неверный выбор!")
            null
        }
    }

    if (operation != null) {
        println("Введите числа через пробел:")
        val input = readLine()?.split(" ")?.mapNotNull { it.toIntOrNull() }

        if (input != null) {
            val result = operation(input)
            println("Результат: $result")
        }
        else {
            println("Некорректный ввод.")
        }
    }
}