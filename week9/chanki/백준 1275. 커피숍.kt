lateinit var elementsOfSegmentTree: IntArray
lateinit var segmentTree: LongArray

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val firstLineInput = readLine().split(" ")
    val numberOfTurns = firstLineInput[1].toInt()

    elementsOfSegmentTree = readLine().split(" ").map { it.toInt() }.toIntArray()
    initializeSegmentTree()

    val sb = StringBuilder()
    repeat(numberOfTurns) {
        // 값 찾기
        val problem = readLine().split(" ")
        val range1 = problem[0].toInt()
        val range2 = problem[1].toInt()
        val value = findSegmentTreeElement(
            range1.coerceAtMost(range2),
            range2.coerceAtLeast(range1),
            1,
            elementsOfSegmentTree.size,
            1
        )
        sb.append(value).append("\n")

        // 교환
        val swapIndex = problem[2].toInt()
        // Int.MAX_VALUE - (Int.MIN_VALUE) 하면 Long 범위이므로 형변환 필요
        val valueGap = elementsOfSegmentTree[swapIndex - 1].toLong() - problem[3].toLong()
        elementsOfSegmentTree[swapIndex - 1] = problem[3].toInt()
        swapElementOfSegmentTree(1, elementsOfSegmentTree.size, 1, swapIndex, valueGap)
    }

    println(sb)
}

// 세그먼트 트리 초기화
private fun initializeSegmentTree() {
    segmentTree = LongArray(elementsOfSegmentTree.size * 4 + 1)
    initializeElementOfSegmentTree(1, elementsOfSegmentTree.size, 1)
}

// 누적합 형태의 세그먼트 트리
private fun initializeElementOfSegmentTree(start: Int, end: Int, index: Int) {
    if (start == end) {
        segmentTree[index] = elementsOfSegmentTree[start - 1].toLong()
        return
    }

    var sum = 0L
    for (i in IntRange(start - 1, end - 1)) {
        sum += elementsOfSegmentTree[i]
    }
    segmentTree[index] = sum

    val mid = (start + end) / 2
    initializeElementOfSegmentTree(start, mid, index * 2)
    initializeElementOfSegmentTree(mid + 1, end, index * 2 + 1)
}

// 누적합 형태의 세그먼트 트리 탐색
private fun findSegmentTreeElement(problemStart: Int, problemEnd: Int, start: Int, end: Int, index: Int): Long {
    if (problemEnd < start || problemStart > end) {
        return 0L
    }

    if (problemStart <= start && end <= problemEnd) {
        return segmentTree[index]
    }

    val mid = start + (end - start) / 2
    val left = findSegmentTreeElement(problemStart, problemEnd, start, mid, index * 2)
    val right = findSegmentTreeElement(problemStart, problemEnd, mid + 1, end, index * 2 + 1)

    return left + right
}

// 세그먼트 트리의 값 교체
private fun swapElementOfSegmentTree(start: Int, end: Int, index: Int, swapIndex: Int, valueGap: Long) {
    if (swapIndex < start || end < swapIndex) {
        return
    }

    // 누적합 형태의 세그먼트 트리이므로, 해당 인덱스가 포함되어있는 노드는 모두 교체값을 제외해주어야 함
    segmentTree[index] = segmentTree[index] - valueGap

    if (start == end) {
        return
    }

    val mid = start + (end - start) / 2
    swapElementOfSegmentTree(start, mid, index * 2, swapIndex, valueGap)
    swapElementOfSegmentTree(mid + 1, end, index * 2 + 1, swapIndex, valueGap)
}
