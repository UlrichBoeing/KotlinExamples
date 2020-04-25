import kotlin.random.Random

fun main() {
    var arr = FloatArray(10)  {1.0f}
    var list = (DoubleArray(10)  {i -> Math.sin(i.toDouble())}).toMutableList()
    list.add(5.0)

    for (i in list) {
        println(i)
    }

    val v1 = Vec(10f, 20f)
    val v2 = Vec(5f, 3f)
    println((v1 + v2)())
}

data class Vec(val x: Float, val y: Float) {
    operator fun plus(other: Vec): Vec {
        return Vec(x + other.x, y + other.y)
    }

    operator fun get(i: Int): Float {
        if (i == 0)
            return x
        else
            return y
    }

    operator fun invoke(): Float {
        return x + y
    }
}