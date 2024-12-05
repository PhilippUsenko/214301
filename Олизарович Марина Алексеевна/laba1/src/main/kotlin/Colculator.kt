fun main() {
    while (true) {
        println(
            "Нажмите 1, чтобы воспользоваться обычным колькулятором\n" +
                    "Нажмите 2, чтобы перевести граммы в тонны\n" +
                    "Нажмите 3, чтобы перевести дюймы в сантиметры\n" +
                    "Нажмите 0, для выхода"
        )
        var input = readln();
        if (input == "1") {
            println("Введите выражение")
            val text = readln()
            asd(text)
        } else if (input == "2") {
            println("Введите количество грамм")
            val gramm = readln();
            gramm(gramm)
        } else if (input == "3") {
            println("Введите количество дюйм")
            val inch = readln()
            inch(inch)
        } else if (input == "0") {
            break
        } else {
            println("Неверный ввод, повторите заново")
        }
    }
}
fun zxc(tokens: MutableList<String>): Double {
    var a=0.0
    var indexofoperator =0
    if("*" in tokens){
            indexofoperator = tokens.indexOf("*")
            a=tokens[indexofoperator-1].toDouble()*tokens[indexofoperator+1].toDouble()
            tokens[indexofoperator]=a.toString()
            tokens.removeAt(indexofoperator-1)
        tokens.removeAt(indexofoperator)
        zxc(tokens)
    }
    if("/" in tokens){
        indexofoperator = tokens.indexOf("/")
        a=tokens[indexofoperator-1].toDouble()/tokens[indexofoperator+1].toDouble()
        tokens[indexofoperator]=a.toString()
        tokens.removeAt(indexofoperator-1)
        tokens.removeAt(indexofoperator)
        zxc(tokens)
    }
    if("+" in tokens){
        indexofoperator = tokens.indexOf("+")
        a=tokens[indexofoperator-1].toDouble()+tokens[indexofoperator+1].toDouble()
        tokens[indexofoperator]=a.toString()
        tokens.removeAt(indexofoperator-1)
        tokens.removeAt(indexofoperator)
        zxc(tokens)
    }
    if("-" in tokens){
        indexofoperator = tokens.indexOf("-")
        a=tokens[indexofoperator-1].toDouble()-tokens[indexofoperator+1].toDouble()
        tokens[indexofoperator]=a.toString()
        tokens.removeAt(indexofoperator-1)
        tokens.removeAt(indexofoperator)
        zxc(tokens)
    }

    return tokens[0].toDouble()
}
fun asd(expression: String){
    val tokens = expression.split(" ").toMutableList()
    val result = zxc(tokens)
    println("$expression = $result")
}


fun default(input: String) {
    if ("+" in input) {
        val result = input.substringBefore("+").toDouble() + input.substringAfter("+").toDouble()
        println("$input = $result")
    } else if ("-" in input) {
        val result = input.substringBefore("-").toDouble() - input.substringAfter("-").toDouble()
        println("$input = $result")
    } else if ("*" in input) {
        val result = input.substringBefore("*").toDouble() * input.substringAfter("*").toDouble()
        println("$input = $result")
    } else if ("/" in input) {
        val result = input.substringBefore("/").toDouble() / input.substringAfter("/").toDouble()
        println("$input = $result")
    }
}

fun gramm(input: String) {
    val ton = input.toDouble() * 0.000001
    println("$input грамм это $ton тонн")
}

fun inch(input: String) {
    val sm = input.toDouble() * 2.54
    println("$input дюймов это $sm саетиметров")
}