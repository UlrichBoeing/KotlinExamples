import de.ulrich_boeing.basics.*
import de.ulrich_boeing.processing.fillOnly
import de.ulrich_boeing.quadtree.Quadtree
import de.ulrich_boeing.quadtree.createIntArray
import processing.core.PApplet
import de.ulrich_boeing.utility.MouseTrace
import de.ulrich_boeing.utility.Timespan
import kotlin.random.Random

class MyApplet : PApplet() {
    companion object {
        val myApplet = MyApplet()
        fun runSketch() {
            myApplet.setSize(800, 600)
            myApplet.runSketch()
        }
    }

    val appWindow by lazy { Rect(width, height) }

    val mouseTrace = MouseTrace(this)// .setSpread(200, 100f)

    var timer1 = Timespan("query", Timespan.Unit.MILLI)
    var timer2 = Timespan("sortNodes", Timespan.Unit.MILLI)

    val numParticles = 50000
    val numQueries = 50000

    override fun setup() {
        background(255)
    }

    override fun draw() {
        val list = appWindow.listOfRandomVec(numParticles)
        val searchCircle = Circle(mouseX, mouseY, 25f)
        val searchRect = Rect(mouseX, mouseY, 50, 50)

        // Building list of particles
//        val list = mouseTrace.update()
        val factor = Random.nextFloat() * 3.5f + 1f
        val startValue = Random.nextInt(1, 1000)
        val capacity =  createIntArray(16, 3f)
        val quadtree = Quadtree(list, appWindow, capacity)

        // Building tree

//        timer1.start()
//        val found1 = quadtree.query(searchRect)
//        timer1.end().println()
        timer2.start()
        for (i in 1..numQueries) {
            val x = Random.nextFloat() * width
            val y = Random.nextFloat() * height
            val rect = Rect(x, y, 100f, 100f)
            val found = quadtree.queryRect(rect)
//            val found2 = quadtree.getNumberOfVecs(rect)
//            println("$found2 - ${found - found2}")
        }
        timer2.end().println()
//        println("for factor $factor " + capacity.contentToString())
//        val x = map(factor.toFloat(), 1f, 4.5f, 0f, 800f)
//        val y = map(timer2.lastTime.toFloat(), 0f, 20000f, 0f, 600f)
//        fillOnly(COLOR_RED.setAlpha(120))
//        ellipse(x, 590f - y, 10f, 10f)

//        val queryRect = Rect(mouseX, mouseY, 80, 80)
//        val found = quadtree.query(circle)
//        val (inside, toCheck) = quadtree.sortRects(searchRect)


//        println("Anzahl der Punkte im Suchfeld: " + found2)

//        fillOnly(COLOR_RED.setAlpha(120))
//        toCheck.draw(g)
//        fillOnly(COLOR_YELLOW.setAlpha(200))
//        inside.draw(g)

//        strokeOnly(COLOR_GRAY, 2f)
//        searchRect.draw(g)

//        fillOnly(COLOR_FUCHSIA.setAlpha(180))
//        list.drawAsCircles(g, 6f)
//        quadtree.drawPoints(g, 6f)

//        strokeOnly(COLOR_BLUE, 1f)
//        quadtree.draw(g)
//
//        fillOnly(COLOR_AQUA.setAlpha(250))
//        for (rect in inside)
//            rect.draw(g)

//        fillOnly(COLOR_RED)
//        found2.drawAsCircles(g, 6f)
//        println("" + found1.size + " = " + found2.size)
    }
}

fun simpleFindAll(list: List<Vec>, circle: Circle): List<Vec> {
    val found = mutableListOf<Vec>()
    for (vec in list) {
        if (circle.contains(vec))
            found.add(vec)
    }
    return found
}

fun simpleFindAll(list: List<Vec>, rect: Rect): List<Vec> {
    val found = mutableListOf<Vec>()
    for (vec in list) {
        if (rect.contains(vec))
            found.add(vec)
    }
    return found
}

fun main(args: Array<String>) {
    MyApplet.runSketch()
}

