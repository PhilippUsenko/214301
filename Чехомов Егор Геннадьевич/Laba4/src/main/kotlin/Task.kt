import java.time.LocalDate

data class Task(
    val id: Int,
    val name: String,
    val assignedTo: Participant,
    var status: TaskStatus = TaskStatus.Ожидание,
    val estimatedDays: Long,
    var startDate: LocalDate,
    var dueDate: LocalDate
) {
    fun updateStatus(newStatus: TaskStatus) {
        status = newStatus
    }
}
