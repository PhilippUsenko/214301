import kotlin.math.pow

fun main() {
    print("Введите сумму вклада: ")
    val P = readLine()?.toDoubleOrNull()
    print("Срок заключение договора (лет): ")
    val T = readLine()?.toIntOrNull()
    var sum = 0.0

    if (P != null){
        sum = P
    }

    if (P != null && T != null) {
        for (i in 1..T) {
            print("Введите процентную ставку за $i-й год (в процентах): ")
            val N = readLine()?.toDoubleOrNull()
            if (N != null){
                sum = (sum * (1 + N/1200).pow(12))
            }
        }
        print("Итоговая сумма: $sum")
    }
}