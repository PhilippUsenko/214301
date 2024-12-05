data class TreeNode<T>(
    val value: T,
    val left: TreeNode<T>? = null,
    val right: TreeNode<T>? = null
)

fun main() {
    val tree = TreeNode(
        10,
        left = TreeNode(
            5,
            left = TreeNode(3),
            right = TreeNode(7)
        ),
        right = TreeNode(
            15,
            left = TreeNode(12),
            right = TreeNode(18)
        )
    )

    val countLeaves: (TreeNode<Int>?) -> Int = object : (TreeNode<Int>?) -> Int {
        override fun invoke(node: TreeNode<Int>?): Int {
            return if (node == null) {
                0
            } else if (node.left == null && node.right == null) {
                1
            } else {
                invoke(node.left) + invoke(node.right)
            }
        }
    }

    val findMax: (TreeNode<Int>?) -> Int = object : (TreeNode<Int>?) -> Int {
        override fun invoke(node: TreeNode<Int>?): Int {
            return if (node == null) {
                Int.MIN_VALUE
            } else {
                val leftMax = invoke(node.left)
                val rightMax = invoke(node.right)
                maxOf(node.value, leftMax, rightMax)
            }
        }
    }

    val leavesCount = countLeaves(tree)
    println("Количество листьев в дереве: $leavesCount")
    
    val maxValue = findMax(tree)
    println("Максимальное значение в дереве: $maxValue")
}
