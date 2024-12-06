import java.util.Collections.swap

fun main() {
    var magicNumber: Int
    var n: Int

    print("Введите magicNumber: ")
    magicNumber = readLine()?.toIntOrNull() ?: 0

    do {
        print("Введите число n (не меньше 3): ")
        n = readLine()?.toIntOrNull() ?: 0
    } while (n < 3)

    val list = (1..(n - 1) * 3).toMutableList()

    val result = mutableListOf<List<Int>>()

    fun generate(k: Int) {
        if (k == 1) {
            result.add(list.toList())
        } else {
            for (i in 0 until k) {
                generate(k - 1)

                if (k % 2 == 0) {
                    swap(list, 0, k - 1)
                } else {
                    swap(list, i, k - 1)
                }
            }
        }
    }

    generate(list.size)

    var fl = true
    for (perm in result) {

        val sum1 = perm.take(n).sum()
        if (sum1 != magicNumber) continue

        val sum2 = perm.slice(n - 1..2 * n - 2).sum()
        if (sum2 != magicNumber) continue

        val sum3 = perm.slice(2 * n - 2..<(n-1)*3).sum() + perm[0]
        if (sum3 != magicNumber) continue

        fl = false

        var i = 2;

        println("${perm[n-1]}")

        while (i < n)
        {
            println("${perm[n-i]}" + " ".repeat(i) + "${perm[n+i-2]}")
            i++
        }

        println("${perm[0]} ${perm.takeLast(n-2).joinToString(" ")} ${perm[perm.size - n + 1]}")

        break
    }

    if (fl) {
        print("Магического треугольника для таких значений не найдено")
    }

}
