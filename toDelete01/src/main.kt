import controlP5.ColorWheel
import controlP5.ControlP5
import de.ulrich_boeing.basics.*
import de.ulrich_boeing.canvas.CanvasLayer
import de.ulrich_boeing.canvas.CanvasSize
import de.ulrich_boeing.drawables.Drawable
import de.ulrich_boeing.drawables.DrawableData
import de.ulrich_boeing.drawables.complement
import processing.core.PApplet
import processing.core.PConstants
import kotlin.random.Random

class MyApplet : PApplet() {
    companion object {
        val myApplet = MyApplet()
        fun runSketch() {
            myApplet.runSketch()
        }
    }

    val path = "e:/Temp/face1920.jpg"
//    val path = "e:/Temp/desert1920.jpg"
    val canvasLayer: CanvasLayer by lazy { CanvasLayer.createFromImage(this, path) }
    val layerConfig = mutableListOf(
        DrawableData(className = "SimpleCircle"),
        DrawableData(className = "ComplexPolygon", color = COLOR_GRAY),
        DrawableData(className = "FilterGrid"),
        DrawableData(className = "ComplexPolygon", color = COLOR_FUCHSIA, maxOpacity = 3, size = 50f),
        DrawableData(className = "Line")
    ).complement(9)

    val cp5: ControlP5 by lazy { ControlP5(this) }
    val knobGroup: KnobGroup by lazy { KnobGroup(cp5, layerConfig[0].sliderNames()) }
    val colorWheel: ColorWheel by lazy { cp5.addColorWheel("colorWheel", 1920 - 400, 1080 - 400, 400) }
    var controlPressed = false
    var shiftPressed = false
    var altPressed = false
    var activeLayer = listOf(3, 2, 4)

//    var curLayerConfig: DrawableData
//        get() {
//            var index = canvasLayer.curLayer - 1
//            return layerConfig[index]
//        }
//        set(value) {
//            var index = canvasLayer.curLayer - 1
//            layerConfig[index] = value
//        }




    override fun settings() {
        myApplet.fullScreen()
    }

    override fun setup() {
        Drawable.canvasLayer = canvasLayer

        knobGroup.setSliderMaxValues(layerConfig[0].getSliderMaxValues())
        knobGroup.setValues(layerConfig[0].getSliderValues())

        setUIFromData(layerConfig[activeLayer[0]])

        val distance = FloatArray(5) { i -> Random.nextFloat() }.withWeight(3f)
        val colorDif = FloatArray(5) { i -> Random.nextFloat() }.withWeight()
        val values = listOf(distance, colorDif).getValues()

        canvasLayer.curLayer = 4
        canvasLayer.setBlendMode(PApplet.ADD)
        canvasLayer.curLayer = 3

        println(distance)
        println(colorDif)

        println(values)
        println(values.indexOfMax())
    }

    override fun draw() {
        background(255)
        // sidebar
        val widthSidebar = 150f
        fill(32)
        rect(1920 - widthSidebar, 0f, widthSidebar, 1080f)
        // colorWheel
        if (!colorWheel.isMouseOver) {
            colorWheel.setPosition(1920 - widthSidebar, 1080 - 40f)
            colorWheel.setSize(widthSidebar.toInt(), 40)
        } else {
            colorWheel.setPosition(1920 - 400f, 1080 - 400f)
            colorWheel.setSize(400, 400)
        }

        if (mousePressed) {
            val mouse = canvasLayer.previewToStd(Vec(mouseX - 400f, mouseY - 160f))
            if (canvasLayer.contains(mouse)) {
//                println("cur Layer is " + canvasLayer.curLayer)
                for ((i, value) in activeLayer.withIndex()) {
                    val dataWithUI = if (i != 0) layerConfig[value] else setDataFromUI(layerConfig[value])
                    println("daten: " + dataWithUI.className +": " + dataWithUI.size)
                    canvasLayer.curLayer = value +1
                    canvasLayer.add(Drawable.create(mouse, dataWithUI))
                }
            }
        }
        canvasLayer.render()
        image(canvasLayer.getImage(), 400f, 160f)
    }

    override fun keyPressed() {
        if (!handleSpecialKeys(true)) {
            if (!controlPressed) {
                if (keyCode >= 96 && keyCode <= 105) {
                    layerConfig[activeLayer[0]] = setDataFromUI(layerConfig[activeLayer[0]])
                    val numZeroKeyCode = 96
                    val layerIndex = keyCode - numZeroKeyCode -1
                    activeLayer = listOf(layerIndex)
                    println(activeLayer)

                    canvasLayer.curLayer = layerIndex +1
                    println(activeLayer)
                    setUIFromData(layerConfig[activeLayer[0]])
                }
                if (key == 's') {
                    canvasLayer.saveLayers(CanvasSize.OUTPUT)
                }
            } else {
                if (keyCode >= 96 && keyCode <= 105) {
                    val numZeroKeyCode = 96
                    val layerIndex = keyCode - numZeroKeyCode
                    canvasLayer.toggleVisibility(layerIndex)
                }

            }

        }
        println("control: $controlPressed, shift: $shiftPressed, alt: $altPressed")
    }

    override fun keyReleased() {
        handleSpecialKeys(false)
        println("control: $controlPressed, shift: $shiftPressed, alt: $altPressed")
        if (!controlPressed && !shiftPressed)
            println("" )

    }

    fun handleSpecialKeys(pressed: Boolean): Boolean {
        val isKeyCoded = (key == CODED.toChar())
        if (isKeyCoded) {
            when (keyCode) {
                PConstants.CONTROL -> controlPressed = pressed
                PConstants.SHIFT -> shiftPressed = pressed
                PConstants.ALT -> altPressed = pressed
            }
        }
        return isKeyCoded
    }

    fun setUIFromData(data: DrawableData) {
        knobGroup["size"] = data.size
        knobGroup["complexity"] = data.complexity
        knobGroup["variance"] = data.variance
        knobGroup["minOpacity"] = data.minOpacity.toFloat()
        knobGroup["maxOpacity"] = data.maxOpacity.toFloat()
        colorWheel.setRGB(data.color)

        colorWheel.update()
    }

    fun setDataFromUI(data: DrawableData): DrawableData {
        return DrawableData(
            size = knobGroup["size"],
            complexity = knobGroup["complexity"],
            variance = knobGroup["variance"],
            minOpacity = knobGroup["minOpacity"].toInt(),
            maxOpacity = knobGroup["maxOpacity"].toInt(),
            color = colorWheel.rgb,
            targetColor = data.targetColor,
            className = data.className
        )
    }
}

fun main(args: Array<String>) {
    MyApplet.runSketch()
}