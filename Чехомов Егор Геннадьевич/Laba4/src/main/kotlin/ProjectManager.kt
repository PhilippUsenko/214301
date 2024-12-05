import java.time.LocalDate
import java.time.format.DateTimeFormatter


class ProjectManager {
    private val projects = mutableListOf<Project>()
    private var taskIdCounter = 1
    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    fun createProject(name: String) {
        val project = Project(projects.size + 1, name)
        projects.add(project)
        println("Проект '$name' создан.")
    }

    fun addParticipant(projectId: Int, name: String, role: Role) {
        val project = projects.find { it.id == projectId }
        if (project != null) {
            project.addParticipant(Participant(name, role))
        } else {
            println("Проект с ID $projectId не найден.")
        }
    }

    fun addTask(
        projectId: Int,
        name: String,
        participantName: String,
        estimatedDays: Long,
        startDate: String
    ) {
        val project = projects.find { it.id == projectId }
        if (project != null) {
            val participant = project.getParticipants().find { it.name == participantName }
            if (participant != null) {
                val start = LocalDate.parse(startDate, dateFormatter)
                val due = start.plusDays(estimatedDays)
                val task = Task(
                    id = taskIdCounter++,
                    name = name,
                    assignedTo = participant,
                    estimatedDays = estimatedDays,
                    startDate = start,
                    dueDate = due
                )
                project.addTask(task)
                project.trackProgress()
            } else {
                println("Участник с именем '$participantName' не найден в проекте.")
            }
        } else {
            println("Проект с ID $projectId не найден.")
        }
    }

    fun updateTaskStatus(projectId: Int, taskId: Int, newStatus: TaskStatus) {
        val project = projects.find { it.id == projectId }
        project?.updateTaskStatus(taskId, newStatus)
            ?: println("Проект с ID $projectId не найден.")
    }

    fun recalculateDeadlines(projectId: Int) {
        val project = projects.find { it.id == projectId }
        project?.recalculateDeadlines()
            ?: println("Проект с ID $projectId не найден.")
    }

    fun displayProjects() {
        if (projects.isEmpty()) {
            println("Проекты отсутствуют.")
        } else {
            println("Список проектов:")
            projects.forEach { project ->
                println("ID: ${project.id}, Название: '${project.name}'")
            }
        }
    }

    fun displayProjectTasks(projectId: Int) {
        val project = projects.find { it.id == projectId }
        project?.displayTasks()
            ?: println("Проект с ID $projectId не найден.")
    }
}
