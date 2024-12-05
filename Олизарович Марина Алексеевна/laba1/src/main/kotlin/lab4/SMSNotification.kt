package lab4

class SMSNotification(message: String) : Notification(message) {
    override fun sendNot() {
        println("sent sms: $message")
    }
}