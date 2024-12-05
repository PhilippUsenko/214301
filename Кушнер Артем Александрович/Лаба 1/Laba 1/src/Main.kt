import kotlin.random.Random

fun main() {
    val combinationLength = 4
    val maxDigit = 9

    val secretCombination = generateCombination(combinationLength, maxDigit)
    println("Программа загадала комбинацию чисел. Попробуйте угадать!")

    var attempts = 0
    var guessedCorrectly = false

    while (!guessedCorrectly) {
        attempts++
        println("Введите $combinationLength чисел (от 0 до $maxDigit) через пробел:")
        val userInput = readLine()?.split(" ")?.map { it.toIntOrNull() ?: -1 } ?: emptyList()

        if (userInput.size != combinationLength || userInput.any { it < 0 || it > maxDigit }) {
            println("Неверный ввод. Попробуйте снова.")
            continue
        }

        val (correctPositions, correctNumbers) = evaluateGuess(userInput, secretCombination)
        if (correctPositions == combinationLength) {
            guessedCorrectly = true
            println("Поздравляем! Вы угадали комбинацию за $attempts попыток.")
        } else {
            println("Правильных чисел на своих местах: $correctPositions")
            println("Правильных чисел, но не на своих местах: ${correctNumbers - correctPositions}")
        }
    }
}

fun generateCombination(length: Int, maxDigit: Int): List<Int> {
    return List(length) {
        Random.nextInt(0, maxDigit + 1)
    }
}

fun evaluateGuess(guess: List<Int>, secret: List<Int>): Pair<Int, Int> {
    var correctPositions = 0
    var correctNumbers = 0
    val secretCopy = secret.toMutableList()
    val guessCopy = guess.toMutableList()

    for (i in guess.indices) {
        if (guess[i] == secret[i]) {
            correctPositions++
            secretCopy[i] = -1  
            guessCopy[i] = -1
        }
    }

    for (i in guess.indices) {
        if (guessCopy[i] != -1 && guessCopy[i] in secretCopy) {
            correctNumbers++
            secretCopy[secretCopy.indexOf(guessCopy[i])] = -1
        }
    }

    return Pair(correctPositions, correctNumbers)
}
