interface Observer {
    fun update(messege: String)
}

class Notification {
    private val observers = mutableListOf<Observer>()
    var messege: String= ""
        set(value) {
            field = value
            notifyAllObservers()
        }

    fun attach(observer: Observer) = observers.add(observer)
    private fun notifyAllObservers() = observers.forEach { it.update(messege) }
}

class EmailNotification : Observer {
    override fun update(messege: String) =
        println("EmailNotification: ${messege}")
}

class SMSNotification : Observer {
    override fun update(messege: String) =
        println("SMSNotification: ${messege}")
}
class PushNotification : Observer {
    override fun update(messege: String) =
        println("PushNotification: ${messege}")
}

fun main() {
    val notification = Notification()
    notification.attach(EmailNotification())
    notification.attach(SMSNotification())
    notification.attach(PushNotification())
    notification.attach(PushNotification())

    notification.messege = "1 update"
    notification.messege = "2 update"
}