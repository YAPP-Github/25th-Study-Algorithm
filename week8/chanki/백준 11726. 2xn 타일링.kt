import java.io.BufferedReader
import java.io.InputStreamReader

lateinit var memoization: IntArray

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val column = readLine().toInt()
    // 메모이제이션 배열
    memoization = IntArray(column + 1).apply {
        this[0] = 1
        this[1] = 1
    }
    println(dfs(column) % 10007)
}

fun dfs(column: Int): Int {
    // 이미 계산된 column이라면 그대로 반환
    if (memoization[column] != 0) {
        return memoization[column]
    }

    // 2 * column은 (column - 1)과 (column - 2)를 계산한 합과 같음
    // 예시) 가령 2*7의 경우
    // 2*6에서 세로 막대기 하나 추가 + 2*5에서 가로 막대기 2개 추가
    return (dfs(column - 1) + dfs(column - 2)).also { memoization[column] = it % 10007 }
}
