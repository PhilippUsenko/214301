fun main() {
    val numbers = arrayOf(-5, 3, -2, 9, -7, 1, -1, 4, 0)
    val negatives = mutableListOf<Int>()
    val positives = mutableListOf<Int>()

    for (num in numbers) {
        if (num < 0) {
            negatives.add(num)
        } else {
            positives.add(num)
        }
    }

    for (i in 0 .. negatives.size - 2) {
        for (j in 0 .. negatives.size - 2 - i) {
            if (negatives[j] < negatives[j + 1]) {
                val temp = negatives[j]
                negatives[j] = negatives[j + 1]
                negatives[j + 1] = temp
            }
        }
    }

    for (i in 0 .. positives.size - 2) {
        for (j in 0 .. positives.size - 2 - i) {
            if (positives[j] > positives[j + 1]) {
                val temp = positives[j]
                positives[j] = positives[j + 1]
                positives[j + 1] = temp
            }
        }
    }

    for (i in negatives) {
        print("${i}, ")
    }
    for (i in positives) {
        print("${i}, ")
    }
}
