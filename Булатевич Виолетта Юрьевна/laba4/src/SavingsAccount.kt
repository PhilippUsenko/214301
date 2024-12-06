class SavingsAccount(accountNumber: String, balance: Double, val interestRate: Double)
    : BankAccount(accountNumber, balance) {

    fun addInterest() {
        val interest = balance * (interestRate / 100)
        balance += interest
        println("Процент $interest был начислен. Новый баланс: $balance")
    }

    fun predictFutureBalance(years: Int): Double {
        val futureBalance = balance * Math.pow(1 + interestRate / 100, years.toDouble())
        return futureBalance
    }
}