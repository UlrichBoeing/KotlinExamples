import controlP5.ControlP5
import controlP5.Knob

class KnobGroup(val cp5: ControlP5, val names: List<String>) {
    val size = names.size
    val knob = Array<Knob>(names.size) { i ->
        val width = 150f
        val height = 140f
        val radius = 36f
        cp5.addKnob(names[i])
            .setPosition(1920f - width / 2 - radius * 0.75f, 260f + (height - radius) * i)
            .setRadius(radius)
            .setDragDirection(ControlP5.VERTICAL)
            .setResolution(-200f)
            .setCaptionLabel(names[i])
    }

    fun setSliderMaxValues(maxValues: List<Float>) = maxValues.forEachIndexed { i, max -> knob[i].setRange(0f, max) }
    fun setValues(values: List<Float>) = values.forEachIndexed { i, f -> knob[i].value = f }
    fun getValues(): FloatArray = FloatArray(size) { i -> knob[i].value }

    operator fun get(name: String):Float {
        return cp5[name].value
    }

    operator fun set(name: String, value: Float) {
        cp5[name].value = value
    }
}