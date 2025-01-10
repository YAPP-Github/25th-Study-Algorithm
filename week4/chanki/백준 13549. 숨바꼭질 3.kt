import java.io.BufferedReader
import java.io.InputStreamReader

class B13549KtNode(
    val point: Int,
    val time: Int
)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(" ")
    val start = input[0].toInt()
    val end = input[1].toInt()

    // start >= end인 경우, -1씩 이동하여 좌측으로 이동하는 방법 밖에 없다.
    if (start >= end) {
        println(start - end)
        return
    }

    val deque = ArrayDeque<B13549KtNode>()
    deque.add(B13549KtNode(start, 0))

    val minimumTimes = IntArray(100001) { Int.MAX_VALUE }
    minimumTimes[start] = 0
    // 0-1 BFS
    while (deque.isNotEmpty()) {
        val node = deque.removeFirst()

        if (node.point - 1 >= 0 && minimumTimes[node.point - 1] > node.time + 1) {
            minimumTimes[node.point - 1] = node.time + 1
            // 현재 기준으로 가중치가 +1인 경우 마지막에 삽입
            deque.addLast(B13549KtNode(node.point - 1, node.time + 1))
        }
        if (node.point + 1 <= 100000 && minimumTimes[node.point + 1] > node.time + 1) {
            minimumTimes[node.point + 1] = node.time + 1
            // 현재 기준으로 가중치가 +1인 경우 마지막에 삽입
            deque.addLast(B13549KtNode(node.point + 1, node.time + 1))
        }
        if (node.point * 2 <= 100000 && minimumTimes[node.point * 2] > node.time) {
            minimumTimes[node.point * 2] = node.time
            // 현재 기준으로 가중치가 +0인 경우 처음에 삽입
            deque.addFirst(B13549KtNode(node.point * 2, node.time))
        }
    }

    println(minimumTimes[end])
}