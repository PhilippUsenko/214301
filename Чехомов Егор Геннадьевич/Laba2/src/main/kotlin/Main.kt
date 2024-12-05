fun main() {
    val array = arrayOf(
        intArrayOf(10, 20, 30, 40),
        intArrayOf(50, 60, 70, 80),
        intArrayOf(5, 10, 15, 20),
        intArrayOf(90, 20, 15, 10)
    )

    val threshold = 100

    val filteredArrays = array.filter { row -> row.sum() > threshold }

    println("Исходный массив:")
    array.forEach { row ->
        println(row.joinToString(", "))
    }

    println("\nСтроки с суммой элементов больше $threshold:")
    filteredArrays.forEach { row ->
        println(row.joinToString(", "))
    }
}
