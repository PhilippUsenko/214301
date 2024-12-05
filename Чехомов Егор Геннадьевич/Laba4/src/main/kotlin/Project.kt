import java.time.temporal.ChronoUnit
import java.time.LocalDate

class Project(
    val id: Int,
    val name: String
) : ParticipantManager, TaskManager, ProgressTracker {
    private val participants = mutableListOf<Participant>()
    private val tasks = mutableListOf<Task>()

    override fun addParticipant(participant: Participant) {
        participants.add(participant)
        println("Участник '${participant.name}' с ролью '${participant.role}' добавлен в проект '${name}'.")
    }

    override fun getParticipants(): List<Participant> = participants

    override fun addTask(task: Task) {
        tasks.add(task)
        println("Задача '${task.name}' добавлена в проект '${name}'.")
    }

    override fun getTasks(): List<Task> = tasks

    override fun updateTaskStatus(taskId: Int, newStatus: TaskStatus) {
        val task = tasks.find { it.id == taskId }
        if (task != null) {
            task.updateStatus(newStatus)
            println("Статус задачи '${task.name}' обновлен на '${newStatus}'.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    override fun recalculateDeadlines() {
        tasks.forEach { task ->
            if (task.status != TaskStatus.Завершено) {
                val daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), task.dueDate)
                if (daysRemaining < 0) {
                    task.dueDate = LocalDate.now().plusDays(task.estimatedDays)
                    println("Срок задачи '${task.name}' пересчитан: новый срок - ${task.dueDate}.")
                }
            }
        }
    }

    override fun trackProgress() {
        val completedTasks = tasks.count { it.status == TaskStatus.Завершено }
        val progress = if (tasks.isNotEmpty()) {
            (completedTasks.toDouble() / tasks.size) * 100
        } else 0.0
        println("Прогресс проекта '${name}': ${"%.2f".format(progress)}%.")
    }

    override fun displayTasks() {
        println("Список задач проекта '${name}':")
        tasks.forEach { task ->
            println(
                "ID: ${task.id}, Название: '${task.name}', Назначен: ${task.assignedTo.name}, " +
                        "Статус: ${task.status}, Дата завершения: ${task.dueDate}"
            )
        }
    }
}
