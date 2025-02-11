import java.io.BufferedReader
import java.io.InputStreamReader

lateinit var matrices: Array<IntArray>
val memoization = Array(500) { IntArray(500) }

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val numberOfCase = readLine().toInt()
    matrices = Array(numberOfCase) { IntArray(2) }
    repeat(numberOfCase) {
        matrices[it] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    println(dfs(0, matrices.lastIndex))
}

// 결과 행렬의 세로*가로와 연산 횟수 반환
private fun dfs(start: Int, end: Int): Int {
    if (memoization[start][end] != 0) {
        return memoization[start][end]
    }
    if (start == end) {
        return 0
    }
    if (end - start == 1) {
        return matrices[start][0] * matrices[start][1] * matrices[end][1]
    }

    var minCountResult = Int.MAX_VALUE
    for (i in IntRange(start, end - 1)) {
        // 왼쪽 범위 연산의 결과
        val left = dfs(start, i)
        // 오른쪽 범위 연산의 결과
        val right = dfs(i + 1, end)
        // 왼쪽 범위와 오른쪽 범위 연산의 결과로 나온 행렬간 곱셈의 횟수
        val count = matrices[start][0] * matrices[i][1] * matrices[end][1]
        // 왼쪽 범위 연산, 오른쪽 범위 연산, 새로운 연산 횟수의 합계
        val totalOperationCount = left + right + count

        minCountResult = minCountResult.coerceAtMost(totalOperationCount)
    }

    return minCountResult.also { memoization[start][end] = it }
}