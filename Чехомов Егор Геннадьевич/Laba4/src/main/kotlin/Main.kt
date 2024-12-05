import kotlin.system.exitProcess


enum class Role {
    Менеджер_Проекта, Разработчик, Тестировщик
}


enum class TaskStatus {
    Ожидание, В_Процессе, Завершено
}




fun main() {
    val manager = ProjectManager()

    while (true) {
        println(
            """
            1. Создать проект
            2. Добавить участника
            3. Добавить задачу
            4. Обновить статус задачи
            5. Пересчитать сроки задач
            6. Показать проекты
            7. Показать задачи проекта
            8. Выйти
            """.trimIndent()
        )
        print("Введите номер команды: ")
        try {
            when (readLine()?.toIntOrNull()) {
                1 -> {
                    print("Введите название проекта: ")
                    val name = readLine()!!
                    manager.createProject(name)
                }

                2 -> {
                    print("Введите ID проекта: ")
                    val projectId = readLine()?.toIntOrNull() ?: throw IllegalArgumentException()
                    print("Введите имя участника: ")
                    val name = readLine()!!
                    print("Введите роль участника (Менеджер Проекта, Разработчик, Тестировщик): ")
                    val role = Role.valueOf(readLine()!!)
                    manager.addParticipant(projectId, name, role)
                }

                3 -> {
                    print("Введите ID проекта: ")
                    val projectId = readLine()?.toIntOrNull() ?: throw IllegalArgumentException()
                    print("Введите название задачи: ")
                    val name = readLine()!!
                    print("Введите имя назначенного участника: ")
                    val participantName = readLine()!!
                    print("Введите количество дней на выполнение задачи: ")
                    val estimatedDays = readLine()?.toLongOrNull() ?: throw IllegalArgumentException()
                    print("Введите дату начала задачи (dd.MM.yyyy): ")
                    val startDate = readLine()!!
                    manager.addTask(projectId, name, participantName, estimatedDays, startDate)
                }

                4 -> {
                    print("Введите ID проекта: ")
                    val projectId = readLine()?.toIntOrNull() ?: throw IllegalArgumentException()
                    print("Введите ID задачи: ")
                    val taskId = readLine()?.toIntOrNull() ?: throw IllegalArgumentException()
                    print("Введите новый статус задачи (Ожидание, В Процессе, Завершено): ")
                    val status = TaskStatus.valueOf(readLine()!!)
                    manager.updateTaskStatus(projectId, taskId, status)
                }

                5 -> {
                    print("Введите ID проекта для пересчета сроков: ")
                    val projectId = readLine()?.toIntOrNull() ?: throw IllegalArgumentException()
                    manager.recalculateDeadlines(projectId)
                }

                6 -> {
                    manager.displayProjects()
                }

                7 -> {
                    print("Введите ID проекта для отображения задач: ")
                    val projectId = readLine()?.toIntOrNull() ?: throw IllegalArgumentException()
                    manager.displayProjectTasks(projectId)
                }

                8 -> {
                    println("Выход из программы.")
                    exitProcess(0)
                }

                else -> println("Неверный выбор. Попробуйте снова.")
            }
        } catch (e: Exception) {
            println("Ошибка ввода. Попробуйте снова.")
        }
    }
}
