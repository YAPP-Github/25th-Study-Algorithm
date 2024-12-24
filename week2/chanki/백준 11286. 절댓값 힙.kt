import java.util.PriorityQueue
import java.util.Scanner
import kotlin.math.absoluteValue

fun main() = with(Scanner(System.`in`)) {
    // 문제의 요구사항에 맞춰 최소힙 선언
    // 절댓값이 같으면 원본을 비교
    val pq = PriorityQueue(compareBy<IntArray> { it[0] }.thenComparing { it -> it[1] })
    val inputCount = readlnOrNull()!!.toInt()
    val sb = StringBuilder()
    for (i in 1..inputCount) {
        val input = readlnOrNull()!!.toInt()
        // 조건에 맞춰 분기문
        when {
            input == 0 -> when (pq.isEmpty()) {
                true -> sb.append("0").append("\n")
                false -> sb.append(pq.poll()[1]).append("\n")
            }

            // 절댓값과 원본을 함께 저장
            else -> pq.add(intArrayOf(input.absoluteValue, input))
        }
    }

    println(sb)
}
