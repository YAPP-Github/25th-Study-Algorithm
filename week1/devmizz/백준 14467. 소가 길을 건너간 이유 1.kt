import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    // 소의 위치가 0 또는 1로 표기되므로, -1로 배열을 초기화 시킨다.
    val cows = IntArray(10) { -1 }
    val commandCount = readlnOrNull()!!.toInt()

    var answer = 0
    for (i in 1..commandCount) {
        val commandSplit = readlnOrNull()!!.split(" ")
        val cow = commandSplit[0].toInt() - 1
        val position = commandSplit[1].toInt()

        // 소의 이전 위치가 확인되어있고 이번 위치와 다르면 길을 건넌 것으로 간주한다.
        if (cows[cow] != -1 && cows[cow] != position) {
            answer++
        }
        // 소의 위치를 저장 또는 수정한다.
        cows[cow] = position
    }

    println(answer)
}
