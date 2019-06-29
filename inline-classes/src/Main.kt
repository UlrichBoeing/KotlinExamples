fun main() {
    val aColor = Color(0xFFFF0000.toInt())
    val redPortion = aColor.red
    println(aColor)
}

inline class Color(val rgba: Int) {
    inline val red: Int
        get() = ((rgba shr 16) and 0xFF)
}




