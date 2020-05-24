import de.ulrich_boeing.basics.Vec
import de.ulrich_boeing.elements.Layout.Companion.app
import processing.core.PApplet

class NoiseField(val app: PApplet, val scale: Float, val center: Vec? = null) : Field {
    override fun rate(vec: Vec): Float {
        return if (center == null)
            app.noise(vec.x * scale, vec.y * scale)
        else
            app.noise(center.x + vec.x * scale, center.y + vec.y * scale)

    }
}