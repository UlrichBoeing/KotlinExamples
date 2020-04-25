import kotlin.random.Random

fun main() {
    val arr: Array<Int> = arrayOf(1, 4, 6, 7)
    for (i in arr.indices) {
        if (i == 1) continue

        println("$i -> ${arr[i]}")

        if (i == 2) break


    }
}

