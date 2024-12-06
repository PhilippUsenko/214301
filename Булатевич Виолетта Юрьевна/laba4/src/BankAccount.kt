open class BankAccount(val accountNumber: String, var balance: Double) {

    open fun transfer(amount: Double, toAccount: BankAccount) {
        if (balance >= amount) {
            balance -= amount
            toAccount.balance += amount
        } else {
            throw IllegalArgumentException("Недостаточно средств на счете")
        }
    }
ф
    open fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("Счет пополнен на $amount. Новый баланс: $balance")
        } else {
            println("Сумма пополнения должна быть положительной.")
        }
    }

    open fun withdraw(amount: Double) {
        if (amount <= 0) throw IllegalArgumentException("Сумма снятия должна быть больше нуля")
        if (amount > balance) throw IllegalArgumentException("Недостаточно средств")
        balance -= amount
        println("Снятие $amount. Новый баланс: $balance")
    }

    open fun displayBalance() {
        println("Баланс счета: $balance")
    }
}
