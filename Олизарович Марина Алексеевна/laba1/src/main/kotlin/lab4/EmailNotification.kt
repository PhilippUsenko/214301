package lab4

class EmailNotification(message: String) : Notification(message) {
    override fun sendNot() {
        println("sent email: $message")
    }
}