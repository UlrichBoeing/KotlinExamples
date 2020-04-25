interface Fitnessable {
    fun getCenter(): String
}

class Circle(val x: Float, val y: Float, val radius: Float) : Fitnessable {
    override fun getCenter(): String {
        return "center is at x=$x, y= $y"
    }
}

fun main(args: Array<String>) {
    val circle = Circle(2f, 5f, 23f)
    fitness(circle)

}

fun fitness(fitnessable: Fitnessable) {
    println(fitnessable.getCenter())
}