//import java.util.Scanner
//
//fun main() {
//    val scanner = Scanner(System.`in`)
//    println("Расширенный калькулятор с преобразованием единиц измерения")
//    println("Введите выражение или 'exit' для выхода:")
//
//    while (true) {
//        val input = scanner.nextLine()
//        if (input.equals("exit", ignoreCase = true)) {
//            break
//        }
//        try {
//            val result = evaluateExpression(input)
//            println("Результат: $result")
//        } catch (e: Exception) {
//            println("Ошибка: ${e.message}")
//        }
//    }
//}
//
//fun evaluateExpression(input: String): Double {
//    // Замените преобразования единиц на нужные вам
//    val convertedInput = convertUnits(input)
//    // Здесь можно добавить логику для разбора арифметического выражения
//    // Для упрощения, мы просто используем eval() из библиотеки exp4j
//    return evaluateArithmetic(convertedInput)
//}
//
//// Преобразование единиц измерения
//fun convertUnits(input: String): String {
//    return when {
//        input.contains("in") -> {
//            val value = input.substringBefore("in").trim().toDouble()
//            "${value * 2.54} cm"
//        }
//        input.contains("lb") -> {
//            val value = input.substringBefore("lb").trim().toDouble()
//            "${value * 0.453592} kg"
//        }
//        else -> input // Возвращаем ввод, если преобразования не требуется
//    }
//}
//
//// Простой метод для выполнения арифметических операций
//fun evaluateArithmetic(expression: String): Double {
//    // Здесь можно использовать стороннюю библиотеку для разбора
//    // В данном случае я просто обрабатываю базовые операции
//    val parts = expression.split(" ")
//    var result = parts[0].toDouble()
//
//    for (i in 1 until parts.size step 2) {
//        val operator = parts[i]
//        val nextValue = parts[i + 1].toDouble()
//        result = when (operator) {
//            "+" -> result + nextValue
//            "-" -> result - nextValue
//            "*" -> result * nextValue
//            "/" -> result / nextValue
//            else -> throw IllegalArgumentException("Неизвестный оператор: $operator")
//        }
//    }
//    return result
//}