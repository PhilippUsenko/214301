class CreditAccount(accountNumber: String, balance: Double, val creditLimit: Double, val percent: Double)
    : BankAccount(accountNumber,balance) {

    override fun displayBalance() {
        if (balance < 0) {
            println("Баланс: $balance (Долг)")
        } else {
            println("Баланс: $balance")
        }
        println("Кредитный лимит: $creditLimit")
    }

    override fun deposit(amount: Double) {
        if (amount <= 0) {
            println("Сумма пополнения должна быть положительной.")
            return
        }

        if (balance < 0) {
            val amountToPayOffDebt = Math.min(amount, -balance)
            balance += amount
            println("Погашение долга на $amountToPayOffDebt. Новый баланс: $balance")
        } else {
            balance += amount
            println("Пополнение на $amount. Новый баланс: $balance")
        }
    }

    override fun withdraw(amount: Double) {
        if (balance + creditLimit >= amount) {
            balance -= amount
            println("Снято $amount. Новый баланс: $balance")
        } else {
            println("Превышен кредитный лимит, недостаточно средств.")
        }
    }

    override fun transfer(amount: Double, toAccount: BankAccount) {
        if (balance + creditLimit >= amount) {
            balance -= amount
            toAccount.balance += amount
            println("Переведено $amount на другой счет. Новый баланс: $balance")
        } else {
            println("Недостаточно средств для перевода.")
        }
    }

    fun calculateDebtAfterMonths(months: Int): Double {
        if (balance < 0) {
            val totalDebt = balance * Math.pow(1 + percent / 100, months.toDouble())
            return totalDebt
        }
        return balance
    }
}