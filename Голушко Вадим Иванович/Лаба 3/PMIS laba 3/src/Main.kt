//8.	Функция с саморекурсией для работы с деревьями:
// Напишите программу, которая использует саморекурсивные лямбда-выражения для обхода и обработки бинарного дерева
// (например, подсчет листьев, поиск максимального значения).

data class TreeNode(val value: Int, var left: TreeNode? = null, var right: TreeNode? = null)

fun main() {
    val root = TreeNode(
        10,
        left = TreeNode(5, left = TreeNode(3), right = TreeNode(7)),
        right = TreeNode(7, left = TreeNode(14, left = TreeNode(13), right = TreeNode(14)), right = TreeNode(2))
    )

    lateinit var countLeaves: (TreeNode?) -> Int
    countLeaves = { node ->
        if (node == null) 0
        else if (node.left == null && node.right == null) 1
        else countLeaves(node.left) + countLeaves(node.right)
    }

    lateinit var findMax: (TreeNode?) -> Int?
    findMax = { node ->
        when {
            node == null -> null
            node.right == null && node.left == null -> node.value
            else -> {
                val leftMax = findMax(node.left)
                val rightMax = findMax(node.right)
                maxOf(node.value, leftMax ?: Int.MIN_VALUE, rightMax ?: Int.MIN_VALUE)
            }
        }
    }

    lateinit var findMin: (TreeNode?) -> Int?
    findMin = { node ->
        when {
            node == null -> null
            node.right == null && node.left == null -> node.value
            else -> {
                val leftMax = findMin(node.left)
                val rightMax = findMin(node.right)
                minOf(node.value, leftMax ?: Int.MIN_VALUE, rightMax ?: Int.MIN_VALUE)
            }
        }
    }

    fun printTree(node: TreeNode?, l: Int){
        if (node!=null) {
            printTree(node.left, l+1)
            for (i in 1..l)
                print("   ")
            println(node.value)
            printTree(node.right, l+1)
        }
    }

    printTree(root, 0)
    println("Количество листьев: ${countLeaves(root)}")
    println("Максимальное значение: ${findMax(root)}")
    println("Минимальное значение: ${findMin(root)}")
}