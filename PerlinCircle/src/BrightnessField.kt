import de.ulrich_boeing.basics.Vec
import de.ulrich_boeing.basics.getBrightness
import processing.core.PImage

class BrightnessField(val image: PImage) : Field {
    val pixels: FloatArray

    init {
        image.loadPixels()
        pixels = FloatArray(image.width * image.height) {
            image.pixels[it].getBrightness() / 255f
        }
        image.updatePixels()
    }

    override fun rate(vec: Vec): Float {
        val index = (vec.x + vec.y * image.width).toInt()
        return pixels[index]
    }
}