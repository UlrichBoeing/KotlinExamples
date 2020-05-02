import de.ulrich_boeing.basics.*
import de.ulrich_boeing.framework.drawAsCircles
import de.ulrich_boeing.processing.fillOnly
import de.ulrich_boeing.processing.strokeOnly
import de.ulrich_boeing.quadtree.Quadtree
import processing.core.PApplet
import de.ulrich_boeing.utility.MouseTrace
import de.ulrich_boeing.utility.Timespan

class MyApplet : PApplet() {
    companion object {
        val myApplet = MyApplet()
        fun runSketch() {
            myApplet.setSize(800, 600)
            myApplet.runSketch()
        }
    }

    val appWindow by lazy { Rect(width, height) }

    val mouseTrace = MouseTrace(this)
    var buildUp = Timespan("buildUp")
    var query = Timespan("query")

    val numParticles = 1000000
    val numQueries = 10000


    override fun setup() {
//        frameRate(4f)
    }

    override fun draw() {
        background(255)


        // Building list of particles
        val list = mouseTrace.update()
//        val list = appWindow.listOfRandomVec(numParticles)

        // Building tree
        buildUp.start()

        val quadtree = Quadtree(appWindow)
        for (vec in list)
            quadtree.insert(vec)

        buildUp.end().println()

        query.start()
//        for (i in 1..numQueries) {
//            val x = Random.nextFloat() * width
//            val y = Random.nextFloat() * height
//            val found = quadtree.query(Rect(x, y, 50f, 50f))
////            found.drawAsCircles(g, 2f)
////            println(found.size)
//        }

        val found = quadtree.query(Rect(mouseX, mouseY, 50, 50))

        query.end().println()


        fillOnly(COLOR_RED.setAlpha(60))
        list.drawAsCircles(g, 6f)
        strokeOnly(COLOR_BLUE, 1f)
        quadtree.draw(g)

        fillOnly(COLOR_RED)
        found.drawAsCircles(g, 6f)
    }
}

fun main(args: Array<String>) {
    MyApplet.runSketch()
}

