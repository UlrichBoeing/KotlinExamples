import processing.core.PApplet

class MyApplet: PApplet() {
    companion object {
        val myApplet = MyApplet()
        fun runSketch() {
            myApplet.setSize(800, 600)
            myApplet.runSketch()
        }
    }

    override fun setup() {
        background(51)
    }

    override fun draw() {
        val x = random(width.toFloat())
        val y = random(height.toFloat())
        noStroke()
        fill(255f, 0f, 0f, 40f)
        ellipse(x, y,32f, 32f)
    }

}

fun main(args: Array<String>) {
    MyApplet.runSketch()
}