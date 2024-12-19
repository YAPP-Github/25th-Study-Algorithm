import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val firstLineSplit = readlnOrNull()!!.split(" ")
    val train = IntArray(firstLineSplit[0].toInt() + 1)

    for (i in 1..firstLineSplit[1].toInt()) {
        val command = readlnOrNull()!!.split(" ")
        val carNumber = command[1].toInt()
        // 비트연산을 이용
        // 1. 각 좌석의 유일성을 보장한다.
        // 2. 착석 여부를 0, 1로 구분한다.
        // 1010과 1110을 예시로
        train[carNumber] = when (command[0]) {
            // 3번째 자리 착석 -> 1010 | 0100 -> 1110
            // 3번째 자리 착석 -> 1110 | 0100 -> 1110 (이미 앉아 있을 때 영향 X)
            "1" -> train[carNumber] or 1.rotateLeft(command[2].toInt() - 1)
            // 3번째 자리 하차 -> 1010 & ~(1 << 2) -> 1010 & 1011 -> 1010 (이미 비어있는 자리여도 영향 X)
            // 3번째 자리 하차 -> 1110 & 1011 -> 1010
            "2" -> train[carNumber] and 1.rotateLeft(command[2].toInt() - 1).inv()
            // 1010 앞으로 이동 -> 10100 & 1111 -> 0100 (맨 앞사람은 하차한 효과)
            "3" -> train[carNumber].rotateLeft(1) and (1.rotateLeft(20) - 1)
            // 1010 뒤로 이동 -> 101 & 1111 -> 101 (하차할 사람 없음)
            else -> train[carNumber].rotateRight(1) and (1.rotateLeft(20) - 1)
        }
    }

    var answer = 0
    // 2진수를 구성하는 모든 값이 겹쳐야 10진수도 중복된다.
    // 따라서 비트 연산의 결과값은 정확히 동일한 2진수 비트 구성을 가져야만 중복된다.
    val uniqueTrainMemory = mutableSetOf<Int>()
    for (i in 1..train.lastIndex) {
        when {
            uniqueTrainMemory.contains(train[i]) -> continue
            else -> {
                answer++
                uniqueTrainMemory.add(train[i])
            }
        }
    }

    println(answer)
}