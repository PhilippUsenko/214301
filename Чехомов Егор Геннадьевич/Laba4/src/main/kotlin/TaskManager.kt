interface TaskManager {
    fun addTask(task: Task)
    fun getTasks(): List<Task>
    fun updateTaskStatus(taskId: Int, newStatus: TaskStatus)
    fun recalculateDeadlines()
    fun displayTasks()
}


