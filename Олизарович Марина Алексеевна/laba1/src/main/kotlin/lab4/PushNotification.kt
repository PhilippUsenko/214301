package lab4

class PushNotification(message: String) : Notification(message) {
    override fun sendNot() {
        println("sent push: $message")
    }
}