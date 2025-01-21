import java.io.BufferedReader
import java.io.InputStreamReader

lateinit var map: Array<IntArray>
val paperByColor = intArrayOf(0, 0)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    map = arrayOf(IntArray(size))

    repeat(size) { index ->
        map[index] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    divideAndConquer(0, 0, size)
    println("${paperByColor[0]}\n${paperByColor[1]}")
}

fun divideAndConquer(startRow: Int, startColumn: Int, size: Int) {
    val startPointPaperValue = map[startRow][startColumn]
    var isOneColor = true

    // 모두 같은 색상인지 검사
    repeat(size) { row ->
        repeat(size) { column ->
            if (map[startRow + row][startColumn + column] != startPointPaperValue) {
                isOneColor = false
            }
        }
    }

    // 모두 같은 색상이면 해당 색상 개수 + 1 & 종료
    if (isOneColor) {
        paperByColor[startPointPaperValue] += 1
        return
    }

    // 4등분
    divideAndConquer(startRow, startColumn, size / 2)
    divideAndConquer(startRow + size / 2, startColumn, size / 2)
    divideAndConquer(startRow, startColumn + size / 2, size / 2)
    divideAndConquer(startRow + size / 2, startColumn + size / 2, size / 2)
}