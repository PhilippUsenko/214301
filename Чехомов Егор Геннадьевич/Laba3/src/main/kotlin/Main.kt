import java.util.LinkedList
import java.util.Scanner

// Тип для функций обработки очереди
typealias QueueOperation = (LinkedList<String>) -> Unit

// Тип для функций обработки стека
typealias StackOperation = (MutableList<String>) -> Unit

fun main() {
    val scanner = Scanner(System.`in`)

    val queue = LinkedList<String>()
    val stack = mutableListOf<String>()

    while (true) {
        println("\nВыберите режим работы:")
        println("1 - Работа с очередью")
        println("2 - Работа со стеком")
        println("3 - Выход")
        when (scanner.nextLine()) {
            "1" -> {
                println("\nРежим работы с очередью.")
                handleQueue(queue, scanner)
            }
            "2" -> {
                println("\nРежим работы со стеком.")
                handleStack(stack, scanner)
            }
            "3" -> {
                println("Завершение работы.")
                return
            }
            else -> {
                println("Некорректный выбор, попробуйте снова.")
            }
        }
    }
}

// Функция для обработки операций над очередью
fun handleQueue(queue: LinkedList<String>, scanner: Scanner) {
    while (true) {
        println("\nТекущая очередь: $queue")
        println("1 - Добавить элемент")
        println("2 - Удалить элемент")
        println("3 - Назад")
        when (scanner.nextLine()) {
            "1" -> {
                println("Введите элемент для добавления в очередь:")
                val element = scanner.nextLine()
                modifyQueue(queue) { q -> q.add(element) }
                println("Элемент добавлен в очередь.")
            }
            "2" -> {
                if (queue.isEmpty()) {
                    println("Очередь пуста, нечего удалять.")
                } else {
                    modifyQueue(queue) { q -> q.poll() }
                    println("Первый элемент удален из очереди.")
                }
            }
            "3" -> return
            else -> println("Некорректный выбор.")
        }
    }
}

// Функция для обработки операций над стеком
fun handleStack(stack: MutableList<String>, scanner: Scanner) {
    while (true) {
        println("\nТекущий стек: $stack")
        println("1 - Добавить элемент")
        println("2 - Удалить элемент")
        println("3 - Назад")
        when (scanner.nextLine()) {
            "1" -> {
                println("Введите элемент для добавления в стек:")
                val element = scanner.nextLine()
                modifyStack(stack) { s -> s.add(element) }
                println("Элемент добавлен в стек.")
            }
            "2" -> {
                if (stack.isEmpty()) {
                    println("Стек пуст, нечего удалять.")
                } else {
                    modifyStack(stack) { s -> s.removeLast() }
                    println("Последний элемент удален из стека.")
                }
            }
            "3" -> return
            else -> println("Некорректный выбор.")
        }
    }
}

// Функция высшего порядка для работы с очередью
fun modifyQueue(queue: LinkedList<String>, operation: QueueOperation) {
    operation(queue)
}

// Функция высшего порядка для работы со стеком
fun modifyStack(stack: MutableList<String>, operation: StackOperation) {
    operation(stack)
}
