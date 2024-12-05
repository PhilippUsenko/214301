fun main() {
    val memoize: (Int) -> Int = {
        val cache = mutableMapOf<Int, Int>()

        fun fibn(n: Int): Int = cache.getOrPut(n) {
            when (n) {
                1 -> 0
                2 -> 1
                else -> fibn(n - 1) + fibn(n - 2)
            }
        }

        fibn(it)
    }

    println(memoize(5))
    println(memoize(20))
    println(memoize(49))
}