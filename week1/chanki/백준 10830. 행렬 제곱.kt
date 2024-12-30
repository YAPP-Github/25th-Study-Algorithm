import java.util.Scanner

// 캐시 역할을 하는 메모이제이션 맵 활용
val memoization = mutableMapOf<Long, Array<IntArray>>()

// 분할 정복을 통한 행렬 제곱 계산
fun calculate(matrix: Array<IntArray>, pow: Long): Array<IntArray> {
    if (pow == 1L) return matrix
    if (memoization.containsKey(pow)) return memoization[pow]!!

    // 분할 정복
    val half = pow / 2
    val matrixA = calculate(matrix, half)
    val matrixB = calculate(matrix, pow - half)

    // 행렬 곱
    val result = Array(matrix.size) { intArrayOf(matrix.size) }
    for (i in matrix.indices) {
        for (j in matrix.indices) {
            var sum = 0
            for (k in matrix.indices) {
                sum += matrixA[i][k] * matrixB[k][j] % 1000
            }
            result[i][j] = sum % 1000
        }
    }

    // 메모이제이션 저장
    memoization.putIfAbsent(pow, result)

    return result
}

fun main() = with(Scanner(System.`in`)) {
    // 첫 줄 처리
    val splitFirstLine = readlnOrNull()!!.split(" ")
    val size = splitFirstLine[0].toInt()
    val pow = splitFirstLine[1].toLong()

    // 나머지 줄 처리 (행렬 입력)
    val matrix = Array(size) { IntArray(size) }

    for (i in 0..<size) {
        val split = readlnOrNull()!!.split(" ")
        for (splitIndex in split.indices) {
            matrix[i][splitIndex] = split[splitIndex].toInt()
        }
    }

    val sb = StringBuilder()
    calculate(matrix, pow).run {
        for (i in indices) {
            for (j in indices) {
                sb.append(this[i][j] % 1000).append(" ")
            }
            sb.append("\n")
        }
    }

    println(sb)
}