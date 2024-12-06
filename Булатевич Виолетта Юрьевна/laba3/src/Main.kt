import java.util.Stack

fun main() {
    println("Калькулятор. Введите 'exit' для выхода.")

    while (true) {
        print("Введите математическое выражение: ")
        val input = readlnOrNull()?.trim()

        if (input.isNullOrEmpty()) {
            println("Пустой ввод. Попробуйте снова.")
            continue
        }

        if (input.equals("exit", ignoreCase = true)) {
            println("Выход из программы.")
            break
        }

        try {
            val result = evaluateExpression(input)
            println("Результат: $result")
        } catch (e: Exception) {
            println("Ошибка: ${e.message}")
        }
    }
}

fun evaluateExpression(expression: String): Double {
    val tokens = tokenize(expression)
    return parse(tokens)
}

fun tokenize(expression: String): List<String> {
    val tokens = mutableListOf<String>()
    var numberBuffer = StringBuilder()

    for (char in expression) {
        when {
            char.isDigit() || char == '.' -> numberBuffer.append(char)
            char.isWhitespace() -> continue
            else -> {
                if (numberBuffer.isNotEmpty()) {
                    tokens.add(numberBuffer.toString())
                    numberBuffer = StringBuilder()
                }
                if (char in "+-*/()") tokens.add(char.toString())
            }
        }
    }

    if (numberBuffer.isNotEmpty()) tokens.add(numberBuffer.toString())
    return tokens
}

fun parse(tokens: List<String>): Double {
    val stack = Stack<Double>()
    val operatorStack = Stack<String>()
    val precedence = mapOf(
        "+" to 1, "-" to 1,
        "*" to 2, "/" to 2
    )

    fun applyOperation() {
        if (stack.size < 2 || operatorStack.isEmpty()) {
            throw IllegalArgumentException("Некорректное выражение")
        }
        val b = stack.pop()
        val a = stack.pop()

        val result = when (val operator = operatorStack.pop()) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> {
                if (b == 0.0) throw ArithmeticException("Деление на ноль")
                a / b
            }
            else -> throw IllegalArgumentException("Неизвестный оператор $operator")
        }
        stack.push(result)
    }

    for (i in tokens.indices) {
        val token = tokens[i]
        when {
            token.toDoubleOrNull() != null -> stack.push(token.toDouble())
            token in precedence.keys -> {
                while (
                    operatorStack.isNotEmpty() &&
                    precedence[token]!! <= precedence[operatorStack.peek()]!!
                ) {
                    applyOperation()
                }
                operatorStack.push(token)
            }
            token == "(" -> {
                val subExpression = mutableListOf<String>()
                var openBrackets = 1
                var j = i + 1
                while (j < tokens.size) {
                    val subToken = tokens[j]
                    if (subToken == "(") openBrackets++
                    if (subToken == ")") openBrackets--
                    if (openBrackets == 0) break
                    subExpression.add(subToken)
                    j++
                }
                if (openBrackets != 0) {
                    throw IllegalArgumentException("Пропущена закрывающая скобка")
                }
                stack.push(parse(subExpression))
            }
            token == ")" -> throw IllegalArgumentException("Неожиданная закрывающая скобка")
            else -> throw IllegalArgumentException("Неизвестный токен $token")
        }
    }


    while (operatorStack.isNotEmpty()) {
        applyOperation()
    }

    if (stack.size != 1) {
        throw IllegalArgumentException("Некорректное выражение")
    }

    return stack.pop()
}
