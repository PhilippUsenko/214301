class AmphibiousCar : Drivable, Sailable {
    private var mode: Mode = Mode.DRIVE

    enum class Mode {
        DRIVE, SAIL
    }

    fun switchMode(newMode: Mode) {
        mode = newMode
        println("Переключено на ${mode.name.lowercase()} mode.")
    }
    
    override fun drive() {
        if (mode == Mode.DRIVE) {
            println("Едет.")
        } else {
            println("Нельзя ехать в режиме плаванья!")
        }
    }

    override fun sail() {
        if (mode == Mode.SAIL) {
            println("Плывет по воде.")
        } else {
            println("Нельзя плыть в режиме езды!")
        }
    }
}