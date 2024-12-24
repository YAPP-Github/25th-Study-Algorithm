import java.util.Scanner

lateinit var edgeMap: Array<MutableList<Int>>
lateinit var numberOfChildren: IntArray
lateinit var isVisit: BooleanArray

fun main() = with(Scanner(System.`in`)) {
    val (numberOfNode, rootNodeValue, numberOfQuery)
            = readlnOrNull()!!.split(" ").map { it.toInt() }

    edgeMap = Array(numberOfNode + 1) { mutableListOf() }
    numberOfChildren = IntArray(numberOfNode + 1)
    isVisit = BooleanArray(numberOfNode + 1)

    // 간선 정보 저장
    repeat(numberOfNode - 1) {
        val (firstValue, secondValue) = readlnOrNull()!!.split(" ").map { it.toInt() }
        edgeMap[firstValue].add(secondValue)
        edgeMap[secondValue].add(firstValue)
    }

    // 각 노드의 자식 노드 개수 기록을 위한 재귀함수
    countChildren(rootNodeValue)

    val sb = StringBuilder()
    repeat(numberOfQuery) {
        sb.append(numberOfChildren[readlnOrNull()!!.toInt()]).append("\n")
    }
    println(sb)
}

// 자식 전체를 순회하기 위한 DFS
private fun countChildren(value: Int): Int {
    isVisit[value] = true

    // 자식이 없다면 1 반환
    if (edgeMap[value].isEmpty()) {
        return 1.apply { numberOfChildren[value] = this }
    }

    // 자식이 있다면 아래 자식들을 순회하며 합산
    var count = 1
    for (next in edgeMap[value]) {
        if (isVisit[next]) continue
        count += countChildren(next)
    }
    return count.apply { numberOfChildren[value] = this }
}
