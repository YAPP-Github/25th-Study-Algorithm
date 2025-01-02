import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

// 문제 풀이에 필요한 아이디어
// 눈덩이를 크기 순으로 정렬하고 A B C D 가 있다면
// 두 눈덩이 간 크기의 최소 차는 A, D / B, C로 구성될 때다.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val snowballCount = readLine().toInt()
    val radiusOfSnowball = readLine().split(" ").map { it.toInt() }.sorted().toList()

    var minimumDiff = Integer.MAX_VALUE
    // 레이블을 설정하여 차이가 0인 경우 모든 순회 종료
    // 바깥의 두 눈덩이는 실행 가능한 모든 경우를 실행
    outer@ for (outerLeft in 0..<snowballCount - 3) {
        for (outerRight in snowballCount - 1 downTo outerLeft + 3) {
            val outerSnowmanSize = radiusOfSnowball[outerLeft] + radiusOfSnowball[outerRight]
            var innerLeft = outerLeft + 1
            var innerRight = outerRight - 1

            // 안에 있는 눈덩이는 투포인터로 최적화
            while (innerLeft < innerRight) {
                val innerSnowmanSize = radiusOfSnowball[innerLeft] + radiusOfSnowball[innerRight]
                val snowmanSizeDiff = outerSnowmanSize - innerSnowmanSize

                minimumDiff = minimumDiff.coerceAtMost(abs(snowmanSizeDiff))

                when {
                    snowmanSizeDiff == 0 -> break@outer
                    snowmanSizeDiff > 0 -> innerLeft++
                    else -> innerRight--
                }
            }
        }
    }

    println(minimumDiff)
}
