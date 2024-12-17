import java.util.Scanner

// direction 계산을 위한 초기화
val xDirections = intArrayOf(1, 0, -1, 0)
val yDIrections = intArrayOf(0, -1, 0, 1)

fun main() = with(Scanner(System.`in`)) {
    val dragonCurveCount = readlnOrNull()!!.toInt()

    // 전체 좌표에 각 dragonCurve 표시
    val grid = Array(101) { IntArray(101) }
    for (i in 0..<dragonCurveCount) {
        val split = readlnOrNull()!!.split(" ").map { it.toInt() }
        drawDragonCurve(grid, split[0], split[1], split[2], split[3])
    }

    // 1*1 정사각형 개수 카운트
    var squareCount = 0
    for (i in 0..99) {
        for (j in 0..99) {
            if (grid[i][j] == 1 && grid[i + 1][j] == 1 && grid[i][j + 1] == 1 && grid[i + 1][j + 1] == 1) {
                squareCount++
            }
        }
    }
    println(squareCount)
}

private fun drawDragonCurve(
    grid: Array<IntArray>,
    startX: Int,
    startY: Int,
    direction: Int,
    generation: Int
) {
    // grid 전체를 순회하지 않는다.
    // 이미 마킹이 된 좌표만 회전시킬 수 있도록 한다.
    // components: dragonCurve 점들의 모음
    val components = mutableListOf<List<Int>>()
    val starts = listOf(startX, startY).apply {
        components.add(this)
        grid[this[1]][this[0]] = 1
    }
    // 0세대 작업
    var ends = listOf(startX + xDirections[direction], startY + yDIrections[direction])
        .apply {
            components.add(this)
            grid[this[1]][this[0]] = 1
        }
    // curveComponents: 다음 세대에서 처리해야 하는 점들의 모음
    val componentInThisGeneration = ArrayDeque(components)
    for (g in 1..generation) {
        while (componentInThisGeneration.isNotEmpty()) {
            rotateComponent(componentInThisGeneration.removeFirst(), ends)
                .apply {
                    components.add(this)
                    grid[this[1]][this[0]] = 1
                }
        }
        // 다음 end 좌표 초기화
        ends = rotateComponent(starts, ends)
        // 다음 generation 작업을 위해 Deque에 추가
        componentInThisGeneration.addAll(components)
    }
}

private fun rotateComponent(component: List<Int>, ends: List<Int>): List<Int> =
    // 기준점을 기준으로 90도 회전
    listOf(ends[1] - component[1] + ends[0], component[0] - ends[0] + ends[1])
