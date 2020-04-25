import processing.core.PApplet
import processing.core.PVector
import kotlin.random.Random

class MyApplet : PApplet() {
    companion object {
        val myApplet = MyApplet()
        fun runSketch() {
            myApplet.setSize(800, 600)
            myApplet.runSketch()
        }
    }

    val points = mutableListOf<PVector>()

    override fun setup() {
//        frameRate(1f)

    for (i in 1..10000) {

        val x = random(width.toFloat())
        val y = random(height.toFloat())
        val p = PVector(x, y)
        points.add(p)
        }

    }

    override fun draw() {
        background(51)
//        println(points.size)
        for (i in 1..4000) {
            var p1 = points.random()
            var p2 = points.random()
            val dist = PVector.sub(p2!!, (p1))
            if (dist.mag() < 100 && dist.mag() > 30) {
                p1!!.add(dist.mult(0.5f))

            }
        }

        noStroke()
        fill(255f, 0f, 0f, 6f)

        for (p in points) {
//            println(" " + p.x + " " + p.y)
            ellipse(p.x, p.y, 32f, 32f)
        }
    }


}

fun List<PVector>.random(): PVector? = if (size > 0) get(Random.nextInt(size)) else null



fun main(args: Array<String>) {
    MyApplet.runSketch()
}