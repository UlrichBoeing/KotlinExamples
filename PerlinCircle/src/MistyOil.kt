import de.ulrich_boeing.basics.*
import de.ulrich_boeing.extensions.repeat
import de.ulrich_boeing.extensions.split
import de.ulrich_boeing.extensions.triangle
import processing.core.PApplet
import processing.core.PGraphics
import processing.core.PImage
import kotlin.math.roundToInt

open class Brush(val center: Vec, val size: Float) {
    companion object {
        lateinit var app: PApplet
    }

    fun scaleUpSize(img: SourceImage) = size * img.averageWidthHeight
    fun scaleUpCenter(img: SourceImage) = Vec(center.x * img.width, center.y * img.height)

    fun getClipping(scaledCenter: Vec, scaledSize: Float) = Clipping.fromVertices(
        PApplet.floor(scaledCenter.x - (scaledSize / 2f)),
        PApplet.floor(scaledCenter.y - (scaledSize / 2f)),
        PApplet.ceil(scaledCenter.x + (scaledSize / 2f)),
        PApplet.ceil(scaledCenter.y + (scaledSize / 2f))
    )
}

class SourceImage(val image: PImage) {
    val brightness = BrightnessField(image)
    val blur: BrightnessField
    val width: Int
        get() = image.width
    val height: Int
        get() = image.height

    val averageWidthHeight = (width + height) / 2f
    val clip = Clipping(0, 0, width, height)

    init {
        val blurImage = image.copy()
        blurImage!!.filter(PApplet.BLUR, 10f)
        blur = BrightnessField(blurImage)
        image.loadPixels()
    }

}

class NormMistyOil(center: Vec, size: Float) : Brush(center, size) {

    fun draw(img: SourceImage, destination: PGraphics) {
        val scaledCenter = scaleUpCenter(img)
        val scaledSize = scaleUpSize(img)
        val clip = getClipping(scaledCenter, scaledSize)

        val circle = CircleField(scaledCenter, scaledSize / 2f)
        val noise = NoiseField(app, scaledSize * 0.001f, Vec.fromRandomAngle() * 1000f)

        val intersect = clip.intersect(img.clip)
        val g = app.createGraphics(intersect.width, intersect.height)
        g.beginDraw()
        g.loadPixels()
        for (y in intersect.top until intersect.bottom) {
            for (x in intersect.left until intersect.right) {
                val vec = Vec(x, y)

                val product = Product()
                product.add(
                    circle.rate(vec)
                )
                product.add(
                    noise.rate(vec).split(0.5f)
                )
                product.add(
                    img.brightness.rate(vec),
                    weight = circle.rate(vec)
                )
                product.add(
                    img.blur.rate(vec),
                    weight = 1 - circle.rate(vec)
                )
                val alpha = (product.get().triangle(0.4f) * 255).toInt()
                val index = (x - intersect.left) + (y - intersect.top) * g.width
//                var posIndex = (scaledCenter.x + scaledCenter.y * img.width).toInt()
                var posIndex = (scaledCenter.x.roundToInt() + scaledCenter.y.roundToInt() * img.width)
                posIndex = posIndex.coerceIn(0, img.image.pixels.size -1)

//                var posIndex = x + y * img.width
                val color = img.image.pixels[posIndex]
                g.pixels[index] = color.setAlpha(alpha)
            }
        }
        g.updatePixels()
        g.endDraw()
        destination.image(g, intersect.x.toFloat(), intersect.y.toFloat())
    }
}

