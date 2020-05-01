import de.ulrich_boeing.basics.*
import de.ulrich_boeing.framework.*
import processing.core.PApplet
import processing.core.PImage

class MyApplet : PApplet() {
    companion object {
        val myApplet = MyApplet()
        fun runSketch() {
            myApplet.runSketch()
        }
    }

    val resolution = 1200
    val points = mutableListOf<Vec>()
    var grid = List(resolution.square()) { mutableListOf<Vec>() }
    val startRect by lazy { Rect(0, 0, width, height) }
    val colors = listOf(COLOR_BLUE, COLOR_RED, COLOR_GREEN, COLOR_YELLOW)
    val image: PImage by lazy { loadImage("e:/temp/haus-see-1920.jpg") }


    override fun settings() {
        myApplet.setSize(1920, 1293)
    }

    override fun setup() {
        image.loadPixels()
        for (y in 0 until height)
            for (x in 0 until width) {
                val i = x + y * width
//                println(image.pixels[i].getBrightness())
                val brightness = image.pixels[i].getBrightness()
                if (brightness > 150) {
                    val newPoint = Vec(x, y)
                    points.add(newPoint)
                    addToGrid(newPoint, resolution)

                }
//                    image.pixels[i] = COLOR_BLACK
//                else
//                    image.pixels[i] = COLOR_WHITE

            }
        image.updatePixels()
    }

    override fun draw() {
        background(0)
//        image(image, 0f, 0f)
        var newPoint = Vec(mouseX, mouseY)
        if (mousePressed) {
            if (points.isEmpty()) {
                points.add(newPoint)
                addToGrid(newPoint, resolution)
            } else {
                val lastPoint = points.last()
                if (lastPoint.squareDistance(newPoint) > 2) {
                    for (i in 1..4) {
                        points.add(newPoint)
                        addToGrid(newPoint, resolution)
                        newPoint = newPoint + (Vec.fromRandomAngle() * 2)
                    }
                }
            }
        }
        g.noStroke()
        if (points.isEmpty()) {
            return
        }
        noStroke()
        fill(COLOR_AQUA.setAlpha(20))
        var subGridsIndices = createSubGrids(10, 3)
//        for (subGridIndices in subGridsIndices) {
//            val quad = createQuad(grid, subGridIndices)
//            if (quad != null) {
//                quad.drawAsShape(g)
//            }
//        }
//        subGridsIndices = createSubGrids(resolution, 2)
//        for (subGridIndices in subGridsIndices) {
//            val quad = createQuad(grid, subGridIndices)
//            if (quad != null) {
//                quad.drawAsCurvedShape(g)
//            }
//        }
        fill(COLOR_GREEN.setAlpha(255))
        subGridsIndices = createSubGrids(resolution, 5)
        for (subGridIndices in subGridsIndices) {
            val quad = createQuad(grid, subGridIndices)
            if (quad != null) {
                val index = ((quad[0].x + quad[0].y * width).toInt()).coerceIn(0, image.pixels.size -1)
                fill(image.pixels[index].setAlpha(20))

                quad.drawAsCurvedShape(g)
            }
        }
        fill(COLOR_GREEN.setAlpha(255))
        subGridsIndices = createSubGrids(resolution, 6)
        for (subGridIndices in subGridsIndices) {
            val quad = createQuad(grid, subGridIndices)
            if (quad != null) {
                val index = ((quad[0].x + quad[0].y * width).toInt()).coerceIn(0, image.pixels.size -1)
                fill(image.pixels[index].setAlpha(120))

                quad.drawAsCurvedShape(g)
            }
        }
        fill(COLOR_GREEN.setAlpha(255))
        subGridsIndices = createSubGrids(resolution, 5)
        for (subGridIndices in subGridsIndices) {
            val quad = createQuad(grid, subGridIndices)
            if (quad != null) {
                val index = ((quad[0].x + quad[0].y * width).toInt()).coerceIn(0, image.pixels.size -1)
                fill(image.pixels[index].setAlpha(180).setBlue(255))

                quad.drawAsCurvedShape(g)
            }
        }
        fill(COLOR_GREEN.setAlpha(255))
        subGridsIndices = createSubGrids(resolution, 4)
        for (subGridIndices in subGridsIndices) {
            val quad = createQuad(grid, subGridIndices)
            if (quad != null) {
                val index = ((quad[0].x + quad[0].y * width).toInt()).coerceIn(0, image.pixels.size -1)
                fill(image.pixels[index].setAlpha(80))

                quad.drawAsCurvedShape(g)
            }
        }
//        fill(COLOR_BLUE.setAlpha(130))
//        subGridsIndices = createSubGrids(resolution, 1)
//        for (subGridIndices in subGridsIndices) {
//            val quad = createQuad(grid, subGridIndices)
//            if (quad != null) {
//                quad.drawAsCurvedShape(g)
//            }
//        }
//        fill(COLOR_RED.setAlpha(130))
//        subGridsIndices = createSubGrids(resolution, 1)
//        for (subGridIndices in subGridsIndices) {
//            val quad = createQuad(grid, subGridIndices)
//            if (quad != null) {
////                quad.drawAsCurvedShape(g)
//            }
//        }
//        subGridsIndices = createSubGrids(10, 5)
//        for (subGridIndices in subGridsIndices) {
//            val quad = createQuad(grid, subGridIndices)
//            if (quad != null) {
//                quad.drawAsShape(g)
//            }
//        }

//        fill(COLOR_WHITE)
//        points.drawAsCircles(g, 16f)
//        val midPoint = points.midPointAll()
//
//        val rects = startRect.splitTo4(midPoint)
//        var rectPoints = points.splitByPoint(midPoint)
//        for (i in rects.indices) {
//            fill(colors[i].setAlpha(90))
//            rects[i].draw(g)
//            fill(colors[i].setAlpha(220))
//            rectPoints[i].drawAsCircles(g, 8f)
//        }
//        rectPoints = rectPoints.filter { it.size != 0 }
//        val midPoints = List<Vec>(rectPoints.size) { i -> rectPoints[i].midPointAll() }
//        fill(COLOR_WHITE)
////        midPoints.drawAsCircles(g)
//        if (midPoints.size >= 3) {
//            fill(COLOR_FUCHSIA.setAlpha(120))
//            midPoints.drawAsCurvedShape(g)
//        }
//
//        val index = frameCount % 25
//        grid[index].drawAsCircles(g, 32f)
//
//        val subGrids = createSubGrids(5, 4)
//        for (sub in subGrids)
//            println(grid.sumSize())
//        println()
//

//        fill(COLOR_BLUE.setAlpha(60))
//        points.drawAsCircles(g)
//        val rect1 = Rect(0f, 0f, midPoint.x, midPoint.y)
//        val pointsInRect1 = points.filter { rect1.contains(it) }
//        fill(COLOR_FUCHSIA)
//        pointsInRect1.drawAsCircles(g)
//        fill(COLOR_FUCHSIA.setAlpha(50))
//        rect1.draw(g)
//        val m1 = pointsInRect1.midPoint()
//        fill(COLOR_GREEN)
//        m1.drawAsCircle(g)
    }

    fun addToGrid(vec: Vec, resolution: Int = 10) {
        val width = width / resolution.toFloat()
        val height = height / resolution.toFloat()
        val x = (vec.x / width).toInt()
        val y = (vec.y / height).toInt()
        val index = x + (y * resolution)
        grid[index].add(vec)
    }
}

fun main(args: Array<String>) {
    MyApplet.runSketch()
}

fun createQuad(grid: List<List<Vec>>, indices: IntArray): List<Vec>? {
    val points = grid.filterByIndices(indices).filterEmpty()
    if (points.sizeAll() < 3)
        return null

    val midPoint = points.midPointAll()
    var areas = points.splitAllByPoint(midPoint)
    areas = areas.filter { it.isNotEmpty() }
    if (areas.size > 2)
        return List<Vec>(areas.size) { i -> areas[i].midPoint() }
    else
        return null
}

/**
 * Jeder Punkt wird dem ersten passenden Rechteck zugeordnet,
 * die Funktion geht von nicht überlappenden Rechtecken aus
 */
fun List<Vec>.discreteSplit(rects: List<Rect>): List<List<Vec>> {
    val rectPoints = List(rects.size) { mutableListOf<Vec>() }
    for (vec in this) {
        for (i in rects.indices) {
            if (rects[i].contains(vec)) {
                rectPoints[i].add(vec)
                break
            }
        }
    }
    return rectPoints
}

fun List<List<Vec>>.splitAllByPoint(splitPoint: Vec): List<List<Vec>> {
    val splitPoints = List(4) { mutableListOf<Vec>() }
    for (list in this)
        for (vec in list) {
            if (vec.y <= splitPoint.y)
                if (vec.x <= splitPoint.x)
                    splitPoints[0].add(vec)
                else
                    splitPoints[1].add(vec)
            else
                if (vec.x > splitPoint.x)
                    splitPoints[2].add(vec)
                else
                    splitPoints[3].add(vec)
        }
    return splitPoints
}

fun List<Vec>.splitByPoint(splitPoint: Vec): List<List<Vec>> {
    val splitPoints = List(4) { mutableListOf<Vec>() }
    for (vec in this) {
        if (vec.y <= splitPoint.y)
            if (vec.x <= splitPoint.x)
                splitPoints[0].add(vec)
            else
                splitPoints[1].add(vec)
        else
            if (vec.x > splitPoint.x)
                splitPoints[2].add(vec)
            else
                splitPoints[3].add(vec)
    }
    return splitPoints
}

/**
 * Erzeugt ein IntArray mit den Indizes aller subGrids einer bestimmten Größe
 */
fun createSubGrids(sizeGrid: Int, sizeSubGrid: Int): List<IntArray> {
    // subGrid at origin
    val originGrid = IntArray(sizeSubGrid.square()) { i ->
        val x = i % sizeSubGrid
        val y = i / sizeSubGrid
        x + y * sizeGrid
    }

    val sideLength = (sizeGrid - sizeSubGrid) + 1
    return List(sideLength.square()) { i ->
        val x = i % sideLength
        val y = i / sideLength
        IntArray(originGrid.size) { i -> originGrid[i] + (x + y * sizeGrid) }
    }
}





