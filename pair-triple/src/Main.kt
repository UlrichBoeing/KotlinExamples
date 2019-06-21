import kotlin.math.cos
import kotlin.math.sin

data class RGB(val r: Int, val g: Int, val b: Int)

fun main() {
    val (x, y) = polarToCartesian(0.1)
    println("x = $x and y = $y")

    val rgb = Triple(255, 0, 0)
    println("first of triple = ${rgb.first} \nsecond of triple = ${rgb.second} \nthird of triple = ${rgb.third}")

    val (r, g, b) = RGB(255, 0 , 0)
    println("r = $r, g = $g, b = $b")
}

fun polarToCartesian(angle: Double, r: Double = 1.0): Pair<Double, Double> {
    val x = r * cos(angle)
    val y = r * sin(angle)
    return Pair(x, y)
}
