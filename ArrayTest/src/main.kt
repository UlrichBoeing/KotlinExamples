import kotlin.math.absoluteValue

fun main() {
//    val numbers = (1..50).toList().toIntArray()
    val numbers = arrayOf(2.2f, 4f, 8.7f)
    println(numbers.contentToString())

    val reduced = numbers.any { it > 9f }
    println(reduced)
}

fun Array<Int>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}