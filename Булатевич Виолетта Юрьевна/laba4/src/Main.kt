fun main() {
    // Список счетов с номерами
    val bankAccount = BankAccount("001", 1000.0)
    val creditAccount = CreditAccount("002", 500.0, 1000.0, 5.0)
    val investmentAccount = InvestmentAccount("003", 2000.0)
    val savingsAccount = SavingsAccount("004", 1500.0, 4.0)

    // Список всех счетов
    val accounts = listOf(bankAccount, creditAccount, investmentAccount, savingsAccount)

    var running = true

    while (running) {
        // Отображение меню с выбором счета по номеру
        println("\nВыберите счет по номеру:")
        accounts.forEach { println("${it.accountNumber}. ${it.javaClass.simpleName}") }
        println("Введите номер счета для работы или 'exit' для выхода:")

        val input = readLine()

        if (input == "exit") {
            running = false
            println("Выход из программы...")
            continue
        }

        val selectedAccount = accounts.find { it.accountNumber == input }

        if (selectedAccount != null) {
            // Операции с выбранным счетом
            when (selectedAccount) {
                is CreditAccount -> handleCreditAccount(selectedAccount, accounts)
                is InvestmentAccount -> handleInvestmentAccount(selectedAccount, accounts)
                is SavingsAccount -> handleSavingsAccount(selectedAccount, accounts)
                is BankAccount -> handleBankAccount(selectedAccount, accounts)

            }
        } else {
            println("Неверный номер счета. Попробуйте снова.")
        }
    }
}

// Функции для работы с каждым типом счета (переиспользуем те, что уже были)
fun handleBankAccount(account: BankAccount, accounts: List<BankAccount>) {
    var continueRunning = true
    while (continueRunning) {
        println("\nОперации с обычным счетом ${account.accountNumber}:")
        println("1. Показать баланс")
        println("2. Пополнить счет")
        println("3. Снять со счета")
        println("4. Перевести средства")
        println("5. Перейти в главное меню")

        val action = readLine()?.toIntOrNull()

        when (action) {
            1 -> account.displayBalance()
            2 -> {
                println("Введите сумму для пополнения:")
                val amount = readLine()?.toDoubleOrNull()
                if (amount != null) {
                    account.deposit(amount)
                } else {
                    println("Некорректная сумма.")
                }
            }
            3 -> {
                println("Введите сумму для снятия:")
                val amount = readLine()?.toDoubleOrNull()
                if (amount != null) {
                    account.withdraw(amount)
                } else {
                    println("Некорректная сумма.")
                }
            }
            4 -> {
                println("Введите сумму для перевода:")
                val amount = readLine()?.toDoubleOrNull()
                if (amount != null) {
                    println("Выберите номер счета получателя:")
                    accounts.filter { it != account }.forEach { println("${it.accountNumber}. ${it.javaClass.simpleName}") }
                    val recipientAccountNumber = readLine()

                    val recipientAccount = accounts.find { it.accountNumber == recipientAccountNumber && it != account }
                    if (recipientAccount != null) {
                        account.transfer(amount, recipientAccount)
                    } else {
                        println("Некорректный номер счета получателя.")
                    }
                } else {
                    println("Некорректная сумма.")
                }
            }
            5 -> {
                continueRunning = false
            }
            else -> {
                println("Неверный выбор. Попробуйте снова.")
            }
        }
    }
}

fun handleCreditAccount(account: CreditAccount, accounts: List<BankAccount>) {
    var continueRunning = true
    while (continueRunning) {
        println("\nОперации с кредитным счетом ${account.accountNumber}:")
        println("1. Показать баланс")
        println("2. Пополнить счет")
        println("3. Снять со счета")
        println("4. Перевести средства")
        println("5. Рассчитать долг после месяцев")
        println("6. Перейти в главное меню")

        val action = readLine()?.toIntOrNull()

        when (action) {
            1 -> account.displayBalance()
            2 -> {
                println("Введите сумму для пополнения:")
                val amount = readLine()?.toDoubleOrNull()
                if (amount != null) {
                    account.deposit(amount)
                } else {
                    println("Некорректная сумма.")
                }
            }
            3 -> {
                println("Введите сумму для снятия:")
                val amount = readLine()?.toDoubleOrNull()
                if (amount != null) {
                    account.withdraw(amount)
                } else {
                    println("Некорректная сумма.")
                }
            }
            4 -> {
                println("Введите сумму для перевода:")
                val amount = readLine()?.toDoubleOrNull()
                if (amount != null) {
                    println("Выберите номер счета получателя:")
                    accounts.filter { it != account }.forEach { println("${it.accountNumber}. ${it.javaClass.simpleName}") }
                    val recipientAccountNumber = readLine()

                    val recipientAccount = accounts.find { it.accountNumber == recipientAccountNumber && it != account }
                    if (recipientAccount != null) {
                        account.transfer(amount, recipientAccount)
                    } else {
                        println("Некорректный номер счета получателя.")
                    }
                } else {
                    println("Некорректная сумма.")
                }
            }
            5 -> {
                println("Введите количество месяцев для расчета долга:")
                val months = readLine()?.toIntOrNull()
                if (months != null) {
                    val totalDebt = account.calculateDebtAfterMonths(months)
                    println("Прогнозируемый долг после $months месяцев: $totalDebt")
                } else {
                    println("Некорректное количество месяцев.")
                }
            }
            6 -> {
                continueRunning = false
            }
            else -> {
                println("Неверный выбор. Попробуйте снова.")
            }
        }
    }
}

fun handleInvestmentAccount(account: InvestmentAccount, accounts: List<BankAccount>) {
    var continueRunning = true
    while (continueRunning) {
        println("\nОперации с инвестиционным счетом ${account.accountNumber}:")
        println("1. Показать баланс")
        println("2. Добавить вложение")
        println("3. Прогнозировать рост вложений")
        println("4. Продать вложение")
        println("5. Перейти в главное меню")

        val action = readLine()?.toIntOrNull()

        when (action) {
            1 -> account.displayBalance()
            2 -> {
                println("Введите сумму для вложения:")
                val amount = readLine()?.toDoubleOrNull()
                if (amount != null) {
                    println("Введите процентную ставку для вложения:")
                    val rate = readLine()?.toDoubleOrNull()
                    if (rate != null) {
                        account.addInvestment(Investment(amount, rate))
                    } else {
                        println("Некорректная процентная ставка.")
                    }
                } else {
                    println("Некорректная сумма вложения.")
                }
            }
            3 -> account.forecastInvestments()
            4 -> {
                println("Введите индекс вложения для продажи:")
                val index = readLine()?.toIntOrNull()
                if (index != null) {
                    account.sellInvestment(index)
                } else {
                    println("Некорректный индекс вложения.")
                }
            }
            5 -> {
                continueRunning = false
            }
            else -> {
                println("Неверный выбор. Попробуйте снова.")
            }
        }
    }
}

fun handleSavingsAccount(account: SavingsAccount, accounts: List<BankAccount>) {
    var continueRunning = true
    while (continueRunning) {
        println("\nОперации с сберегательным счетом ${account.accountNumber}:")
        println("1. Показать баланс")
        println("2. Добавить проценты")
        println("3. Прогнозировать будущий баланс")
        println("4. Перейти в главное меню")

        val action = readLine()?.toIntOrNull()

        when (action) {
            1 -> account.displayBalance()
            2 -> account.addInterest()
            3 -> {
                println("Введите количество лет для прогноза:")
                val years = readLine()?.toIntOrNull()
                if (years != null) {
                    val futureBalance = account.predictFutureBalance(years)
                    println("Прогнозируемый баланс через $years лет: $futureBalance")
                } else {
                    println("Некорректное количество лет.")
                }
            }
            4 -> {
                continueRunning = false
            }
            else -> {
                println("Неверный выбор. Попробуйте снова.")
            }
        }
    }
}
