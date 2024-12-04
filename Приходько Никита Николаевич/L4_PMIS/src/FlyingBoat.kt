class FlyingBoat : Flyable, Sailable {
    private var mode: Mode = Mode.SAIL

    enum class Mode {
        FLY, SAIL
    }

    fun switchMode(newMode: Mode) {
        mode = newMode
        println("Переключено на ${mode.name.lowercase()} mode.")
    }

    override fun fly() {
        if (mode == Mode.FLY) {
            println("Летит по небу.")
        } else {
            println("Нельзя лететь в режиме плаванья!")
        }
    }

    override fun sail() {
        if (mode == Mode.SAIL) {
            println("Плывет по воде.")
        } else {
            println("Нельзя плыть в режиме полета!")
        }
    }
}