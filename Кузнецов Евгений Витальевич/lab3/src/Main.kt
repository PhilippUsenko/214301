typealias Matrix = List<List<Double>>

fun main() {
    val transpose: (Matrix) -> Matrix = { matrix ->
        val rows = matrix.size
        val cols = matrix[0].size
        List(cols) { col -> List(rows) { row -> matrix[row][col] } }
    }

    val merge: (Matrix, Matrix) -> Matrix = { matrix1, matrix2 ->
        if (matrix1.size != matrix2.size) throw IllegalArgumentException("Кол-во строк матрицы должно совпадать.")
        matrix1.zip(matrix2) { row1, row2 -> row1 + row2 }
    }

    val normalize: (Matrix) -> Matrix = { matrix ->
        val flat = matrix.flatten()
        val max = flat.max()
        val min = flat.min()
        if (max == min) matrix
        else matrix.map { row -> row.map { (it - min) / (max - min) } }
    }

    val matrix1 = listOf(
        listOf(1.0, 2.0, 3.0),
        listOf(4.0, 5.0, 6.0),
        listOf(7.0, 8.0, 9.0)
    )

    val matrix2 = listOf(
        listOf(10.0, 11.0),
        listOf(12.0, 13.0),
        listOf(14.0, 15.0)
    )

    while (true) {
        println(
            """
            Выберите действие:
            1. Транспонирование матрицы
            2. Слияние двух матриц
            3. Нормализация матрицы
            4. Выход
        """.trimIndent()
        )

        print("Введите номер действия: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("Оригинальная матрица:")
                matrix1.forEach { println(it) }
                val transposed = transpose(matrix1)
                println("\nТранспонированная матрица:")
                transposed.forEach { println(it) }
            }

            2 -> {
                println("Матрица 1:")
                matrix1.forEach { println(it) }
                println("\nМатрица 2:")
                matrix2.forEach { println(it) }
                try {
                    val merged = merge(matrix1, matrix2)
                    println("\nСлитая матрица:")
                    merged.forEach { println(it) }
                } catch (e: IllegalArgumentException) {
                    println("Ошибка: ${e.message}")
                }
            }

            3 -> {
                println("Оригинальная матрица:")
                matrix1.forEach { println(it) }
                val normalized = normalize(matrix1)
                println("\nНормализованная матрица:")
                normalized.forEach { println(it) }
            }

            4 -> {
                println("Выход из программы.")
                System.exit(0)
            }

            else -> println("Некорректный ввод. Попробуйте снова.")
        }
        println()
    }
}
