import java.io.BufferedReader
import java.io.InputStreamReader

class Node(
    val value: String,
    var next: Node? = null,
) {
    var last: Node = this
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val numberOfUniv = readLine().toInt()
    // 인덱스를 포함한 노드 정보를 초기화합니다.
    val nodeMap = mutableMapOf<Int, Node>()
    repeat(numberOfUniv) {
        nodeMap[it] = Node(readLine())
    }

    var beforeIndex = 0
    repeat(numberOfUniv - 1) {
        val connectionInfo = readLine().split(" ")
        beforeIndex = connectionInfo[0].toInt() - 1
        val afterIndex = connectionInfo[1].toInt() - 1

        val beforeNode = nodeMap[beforeIndex]!!
        val afterNode = nodeMap[afterIndex]!!

        // Node 객체에 last 변수를 선언하여 시간 복잡도를 낮춥니다. O(n^2) -> O(1)
        // 앞선 연결리스트의 마지막에 뒷 노드를 연결
        // last 데이터 갱신
        beforeNode.last.next = afterNode
        beforeNode.last = afterNode.last
    }

    val head = nodeMap[beforeIndex]
    var node: Node? = head
    val sb = StringBuilder()
    while (node != null) {
        sb.append(node.value)
        node = node.next
    }

    println(sb)
}
