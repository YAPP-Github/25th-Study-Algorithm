import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Comparator
import java.util.PriorityQueue

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    // 레벨 높은 순으로 정렬
    val pq = PriorityQueue<Int>(Comparator.reverseOrder())

    readLine()
    readLine().split(" ").forEach { pq.add(it.toInt()) }

    var accumulatedPoints = 0
    while (pq.size > 1) {
        val first = pq.poll()
        val second = pq.poll()
        accumulatedPoints += first + second
        // 레벨 더 높은 것을 pq에 새로 저장
        pq.add(first.coerceAtLeast(second))
    }

    println(accumulatedPoints)
}