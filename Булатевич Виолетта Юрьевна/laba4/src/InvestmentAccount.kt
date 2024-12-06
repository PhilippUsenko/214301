class InvestmentAccount(accountNumber: String, balance: Double) : BankAccount(accountNumber,balance) {
    private val investments = mutableListOf<Investment>()

    fun addInvestment(investment: Investment) {
        if (balance > investment.amount) {
            investments.add(investment)
            balance -= investment.amount
            println("Вложение на сумму ${investment.amount} с процентной ставкой ${investment.interestRate}% добавлено.")
            println("Новый баланс: $balance")
        } else {
            println("Недостаточно средств")
        }
    }

    // Метод для прогнозирования роста каждого вложения
    fun forecastInvestments(): Double {
        var totalForecast = 0.0
        for (investment in investments) {
            totalForecast += investment.forecastGrowth()
        }
        println("Прогнозируемый рост по всем вложениям: $totalForecast")
        return totalForecast
    }

    fun sellInvestment(index: Int) {
        if (index < 0 || index >= investments.size) {
            throw IllegalArgumentException("Некорректный индекс инвестиции")
        }

        val investment = investments[index]
        balance += investment.amount
        investments.removeAt(index)

        println("Инвестиция продана: $investment. Новый баланс: $balance")
    }


    fun displayInvestments() {
        if (investments.isEmpty()) {
            println("Нет вложений.")
        } else {
            investments.forEach { investment ->
                println("Вложение на сумму ${investment.amount}, процентная ставка: ${investment.interestRate}%")
            }
        }
    }
}
data class Investment(var amount: Double, val interestRate: Double) {
    fun forecastGrowth(): Double {
        return amount * (1 + interestRate / 100)
    }
}
