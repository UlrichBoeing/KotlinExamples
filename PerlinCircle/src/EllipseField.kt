import de.ulrich_boeing.basics.Vec
import de.ulrich_boeing.basics.square
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

interface Field {
    fun rate(vec: Vec): Float
}

class CircleField(val center: Vec, val radius: Float): Field {
    override fun rate(vec: Vec): Float {
        val normRadius = center.distance(vec) / radius
        return (1 - normRadius).coerceAtLeast(0f)
    }
}

class EllipseField(val center: Vec, val width: Float, val height: Float, val rotation: Float) : Field {
    fun radiusAtAngle(angle: Float): Float {
        val rotatedAngle = angle - rotation
        val add1 = width.square() * sin(rotatedAngle).square()
        val add2 = height.square() * cos(rotatedAngle).square()
        return (width * height) / (sqrt(add1 + add2))
    }

    fun radiusAtPoint(vec: Vec): Float {
        val centerToVec = vec - center
        return radiusAtAngle(centerToVec.angle)
    }

    /**
     * 1 for vec = center
     * 0 for points outside the ellipse
     */
    override fun rate(vec: Vec): Float {
        val normRadius = center.distance(vec) / radiusAtPoint(vec)
        return (1 - normRadius).coerceAtLeast(0f)
    }

    fun edgePoints(count: Int): List<Vec> {
        return List(count) {
            val angle = Vec.TAU * (it / count.toFloat())
            val normX = cos(angle)
            val normY = sin(angle)
            val radius = radiusAtAngle(angle)
            center + Vec(normX, normY) * radius
        }
    }
}