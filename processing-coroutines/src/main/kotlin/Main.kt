import kotlinx.coroutines.*
import java.io.File
import processing.core.PApplet as PApplet1


class MyApplet : PApplet1() {
    companion object Factory {
        fun run() {
            var art = MyApplet()
            art.setSize(500, 500)
            art.runSketch()
        }
    }

    val fileList =  File("A:\\GoogleDriveUlrich\\Google Fotos").walkBottomUp().toList()
    var index = 1849

    var result: Deferred<Long>? = null
    var sum = 0L

    fun startRoutine() {
        sum = 0
        result = GlobalScope.async {
            for (t in 1L..1_000L) {
                sum += t
                delay(1)
            }
            sum
        }
    }

    override fun setup() {
        startRoutine()
    }

    override fun draw() {
        val image = loadImage(fileList[index].path)
        image(image, 0f, 0f, width.toFloat(), height.toFloat())
        image.loadPixels()

        val sum = image.pixels.fold(0.0) { sum, element ->
            sum + (red(element) / 255.0 - ((blue(element) + green(element))))}
        val str = "Rot-Anteil: " + (sum / image.pixels.size.toFloat())
        textSize(48f)
        text(str, 10f, 50f)
        delay(3000)
        index++


//        for (pixel in image.pixels) {
//            val red = red(pixel)
//            println(red)
//        }


        if (result?.isCompleted == true) {
//            println("frameCount: $frameCount")
//            println("Summe: $sum")
            startRoutine()
        }
    }
}

fun redRatio(color: Int): Double {
    val r = PApplet1.red(color)
}

fun main(args: Array<String>) {
    MyApplet.run()
}