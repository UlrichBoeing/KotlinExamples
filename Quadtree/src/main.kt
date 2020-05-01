import de.ulrich_boeing.basics.*
import de.ulrich_boeing.framework.drawAsCircles
import de.ulrich_boeing.quadtree.IndexQuadtree
import de.ulrich_boeing.quadtree.Quadtree
import processing.core.PApplet
import kotlin.random.Random

class MyApplet : PApplet() {
    companion object {
        val myApplet = MyApplet()
        fun runSketch() {
            myApplet.setSize(800, 600)
            myApplet.runSketch()
        }
    }

    val rect by lazy { Rect(0, 0, width, height) }
//    val quadtree by lazy { IndexQuadtree(rect, 4) }
    var lastPoint = Vec(-1000, -1000)
    var sum = 0L

    override fun setup() {
//        frameRate(4f)

    }

    override fun draw() {
        background(255)

//        if (mousePressed) {
//            val vec = Vec(mouseX, mouseY)
//            if (vec.squareDistance(lastPoint) > 5) {
//                quadtree.insert(vec)
//                lastPoint = vec
//            }
//        }
        val list = mutableListOf<Vec>()
        for (i in 1..1000000) {
            val x = Random.nextFloat() * width
            val y = Random.nextFloat() * height
            val vec = Vec(x, y)
            list.add(vec)
        }
        val time = Timing()
//        val quadtree = IndexQuadtree(list, rect, 40)

            val quadtree = Quadtree(rect, 20)
            for (vec in list)
                quadtree.insert(vec)

//        fill(COLOR_RED.setAlpha(40))
//        noStroke()
//        quadtree.drawPoints(g, 8f)
//
//        noFill()
//        stroke(COLOR_BLUE)
//        strokeWeight(1f)
//        quadtree.draw(g)

//        stroke(COLOR_GREEN)
//        strokeWeight(1f)
//        quadtree.drawIntersection(g, Rect(mouseX, mouseY, 10, 10))

//        quadtree.drawPoints(g, 64f)

        println(time.get())

        for (i in 1..10000) {
            val x = Random.nextFloat() * width
            val y = Random.nextFloat() * height
            val found = quadtree.query(Rect(x, y, 50f, 50f))
//            println(found.size)
        }

//        noStroke()
//        fill(COLOR_FUCHSIA)
//        list.drawAsCircles(g, 10f)
        sum += time.get()
        println(time.get())
        println("Durchschnitt: " + (sum / frameCount.toFloat()))
    }

}

fun main(args: Array<String>) {
    MyApplet.runSketch()
}