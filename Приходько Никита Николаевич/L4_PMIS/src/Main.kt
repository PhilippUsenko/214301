import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    println("Выберите транспортное средство:")
    println("1. AmphibiousCar")
    println("2. FlyingBoat")
    println("3. UniversalVehicle")

    val choice = scanner.nextInt()

    val vehicle: Any = when (choice) {
        1 -> AmphibiousCar()
        2 -> FlyingBoat()
        3 -> UniversalVehicle()
        else -> {
            println("Неверный выбор. Завершение программы.")
            return
        }
    }

    println("Выбранное ТС: ${vehicle::class.simpleName}")

    loop@ while (true) {
        println("\nВыберите действие:")
        println("1. Переключить режим")
        println("2. Выполнить действие")
        println("3. Выйти")

        when (scanner.nextInt()) {
            1 -> {
                if (vehicle is AmphibiousCar) {
                    println("Выберите режим (1: Ехать, 2: Плыть):")
                    val mode = when (scanner.nextInt()) {
                        1 -> AmphibiousCar.Mode.DRIVE
                        2 -> AmphibiousCar.Mode.SAIL
                        else -> {
                            println("Неверный режим.")
                            continue
                        }
                    }
                    vehicle.switchMode(mode)
                } else if (vehicle is FlyingBoat) {
                    println("Выберите режим (1: Лететь, 2: Плыть):")
                    val mode = when (scanner.nextInt()) {
                        1 -> FlyingBoat.Mode.FLY
                        2 -> FlyingBoat.Mode.SAIL
                        else -> {
                            println("Неверный режим.")
                            continue
                        }
                    }
                    vehicle.switchMode(mode)
                } else if (vehicle is UniversalVehicle) {
                    println("Выберите режим (1: Ехать, 2: Лететь, 3: Плыть):")
                    val mode = when (scanner.nextInt()) {
                        1 -> UniversalVehicle.Mode.DRIVE
                        2 -> UniversalVehicle.Mode.FLY
                        3 -> UniversalVehicle.Mode.SAIL
                        else -> {
                            println("Неверный режим.")
                            continue
                        }
                    }
                    vehicle.switchMode(mode)
                }
            }
            2 -> {
                println("Выберите действие (1: Ехать, 2: Лететь, 3: Плыть):")
                when (scanner.nextInt()) {
                    1 -> if (vehicle is Drivable) vehicle.drive() else println("Это ТС не может ездить.")
                    2 -> if (vehicle is Flyable) vehicle.fly() else println("Это ТС не может летать.")
                    3 -> if (vehicle is Sailable) vehicle.sail() else println("Это ТС не может плавать.")
                    else -> println("Неверное действие.")
                }
            }
            3 -> {
                println("Выход из программы.")
                break@loop
            }
            else -> println("Неверный выбор.")
        }
    }
}
