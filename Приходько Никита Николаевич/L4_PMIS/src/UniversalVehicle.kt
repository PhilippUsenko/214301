class UniversalVehicle : Drivable, Flyable, Sailable {
    private var mode: Mode = Mode.DRIVE

    enum class Mode {
        DRIVE, FLY, SAIL
    }

    fun switchMode(newMode: Mode) {
        mode = newMode
        println("Переключено на ${mode.name.lowercase()} mode.")
    }

    override fun drive() {
        if (mode == Mode.DRIVE) {
            println("Едет по дороге.")
        } else {
            println("Нельзя ехать в ${mode.name.lowercase()} mode!")
        }
    }

    override fun fly() {
        if (mode == Mode.FLY) {
            println("Летит по небу.")
        } else {
            println("Нельзя лететь в ${mode.name.lowercase()} mode!")
        }
    }

    override fun sail() {
        if (mode == Mode.SAIL) {
            println("Плывет по воде.")
        } else {
            println("Нельзя плыть ${mode.name.lowercase()} mode!")
        }
    }
}