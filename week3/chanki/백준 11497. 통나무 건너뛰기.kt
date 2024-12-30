import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val caseCount = requireNotNull(readLine()).toInt()
    val sb = StringBuilder()
    repeat(caseCount) {
        val treeCounts = readLine().toInt()
        val treeHeights = readLine().split(" ").map { it.toInt() }.sorted()
        var left = 0
        var right = treeCounts - 1
        var index = 0
        var treeLocation = IntArray(treeCounts)
        var maxDiff = 0
        // 정렬된 나무들을 가지고 정해진 순서로 배치
        // 순서는 인덱스 기준으로 다음과 같음
        // 0 2 4 6 ... 7 5 3 1
        while (left <= right && index < treeCounts) {
            treeLocation[left++] = treeHeights[index++]
            if (index < treeCounts) {
                treeLocation[right--] = treeHeights[index++]
            }
        }

        // 최대 차이를 구한다
        repeat(treeCounts) {
            maxDiff = maxDiff.coerceAtLeast(abs(treeLocation[it] - treeLocation[(it + 1) % treeCounts]))
        }
        sb.append(maxDiff).append("\n")
    }
    println(sb)
}
