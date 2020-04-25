import kotlin.random.Random

fun checkNumber(number: Float): Boolean {
    println("number = $number")
    return number < 8f
}

val anotherName = checkNumber(5f)


fun main() {
    for (i in 1..1) {
        val values = FloatArray(20) { Random.nextFloat() * 10 }
        val allSmaller5 = values.all { anotherName }
        println(allSmaller5)
        println(values.contentToString())
    }
}