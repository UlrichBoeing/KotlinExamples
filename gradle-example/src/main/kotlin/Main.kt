import kotlinx.coroutines.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicLong

fun main(args: Array<String>) {
    val deferredResult: Deferred<Long> = GlobalScope.async {
            var sum = 0L

            for (t in 0..9000000000) {
                sum += t
            }
            sum
    }

    println(deferredResult.isCompleted)
    runBlocking {
        println("end: ${deferredResult.await()}")
    }
    println(deferredResult.isCompleted)
//    Thread.sleep(10000)
//    for (b in arr)
//        print(b)


}