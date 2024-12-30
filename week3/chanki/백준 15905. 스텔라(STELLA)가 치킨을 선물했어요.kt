import java.util.*

// 자바 풀이와 동일하므로 주석 생략
fun main() = with(Scanner(System.`in`)) {
    val attendanceNumber = requireNotNull(readlnOrNull()).toInt()
    val pq = PriorityQueue<IntArray>(Comparator { o1, o2 ->
        when {
            o1[0] == o2[0] -> o1[1] - o2[1]
            else -> o2[0] - o1[0]
        }
    })

    repeat(attendanceNumber) {
        requireNotNull(readlnOrNull())
            .split(" ")
            .map { it.toInt() }
            .apply { pq.add(intArrayOf(this[0], this[1])) }
    }

    var solveProblemNumber = 0
    repeat(5) {
        solveProblemNumber = pq.poll()[0]
    }

    var result = 0
    while (pq.isNotEmpty() && pq.peek()[0] == solveProblemNumber) {
        pq.poll().apply { result++ }
    }

    println(result)
}