import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val firstLineInput = readLine().split(" ")
    val numberOfPoint = firstLineInput[0].toInt()
    val numberOfTeleport = firstLineInput[1].toInt()

    val secondLineInput = readLine().split(" ")
    val start = secondLineInput[0].toInt()
    val end = secondLineInput[1].toInt()

    val teleportMap = hashMapOf<Int, MutableSet<Int>>()
    // 텔레포트 정보 초기화
    repeat(numberOfTeleport) {
        val points = readLine().split(" ")
        val p1 = points[0].toInt()
        val p2 = points[1].toInt()
        teleportMap.putIfAbsent(p1, hashSetOf())?.add(p2)
        teleportMap[p1]?.add(p2)
        teleportMap.putIfAbsent(p2, hashSetOf())
        teleportMap[p2]?.add(p1)
    }

    var result = 0
    val deque = ArrayDeque<Int>()
    // 방문 정보 표시
    val visited = BooleanArray(numberOfPoint + 1)
    deque.add(start)

    // BFS
    outer@ while (deque.isNotEmpty()) {
        val size = deque.size
        for (i in 0..<size) {
            val now = deque.removeFirst()
            if (now == end) break@outer
            // 방문 여부 표시
            visited[now] = true

            // -1 방문
            if (0 < now - 1 && !visited[now - 1]) {
                deque.add(now - 1)
                visited[now - 1] = true
            }
            // +1 방문
            if (numberOfPoint >= now + 1 && !visited[now + 1]) {
                deque.add(now + 1)
                visited[now + 1] = true
            }
            // 텔레포트 이동
            if (teleportMap.containsKey(now)) {
                teleportMap[now]?.filter { !visited[it] }
                    ?.let {
                        deque.addAll(it)
                        it.forEach { p -> visited[p] = true }
                    }
            }
        }
        // 특정 포인트에서 1초 걸려서 갈 수 있는 것들에 대한 처리 완료
        result++
    }
    println(result)
}
