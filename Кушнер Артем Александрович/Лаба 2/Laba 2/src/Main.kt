fun main() {
    println("Введите числа для первого массива через пробел:")
    val array1 = readLine()?.split(" ")?.mapNotNull {
        it.toIntOrNull()
    } ?: emptyList()

    println("Введите числа для второго массива через пробел:")
    val array2 = readLine()?.split(" ")?.mapNotNull {
        it.toIntOrNull()
    } ?: emptyList()
    
    println("Введите минимальное значение числа:")
    val minValue = readLine()?.toIntOrNull() ?: 0

    println("Введите 'true', если числа должны быть четными, и 'false' если нет:")
    val mustBeEven = readLine()?.toBoolean() ?: true

    val filteredArray1 = filterArray(array1, minValue, mustBeEven)
    val filteredArray2 = filterArray(array2, minValue, mustBeEven)

    val intersection = filteredArray1.intersect(filteredArray2).toList()
    val union = (filteredArray1 + filteredArray2).distinct()

    println("Пересечение массивов: $intersection")
    println("Объединение массивов: $union")
}

fun filterArray(array: List<Int>, minValue: Int, mustBeEven: Boolean): List<Int> {
    return array.filter {
        it >= minValue && (!mustBeEven || it % 2 == 0)
    }
}
