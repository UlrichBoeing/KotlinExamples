
private enum class CanvasSize(private var size: Float){
    XS(0.4f), S(0.7f), M(1f), L(12f), XL(20f);

    fun setSize(newSize: Float) {
        size = newSize
        println("Die Größe ist: " + size)
    }
}

fun main() {
    CanvasSize.S.setSize(0.8f)
}