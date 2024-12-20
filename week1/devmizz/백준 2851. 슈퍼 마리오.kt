import java.util.Scanner
import kotlin.math.abs

// 알고리즘 자체는 동일하므로 설명 생략
fun main(): Unit = with(Scanner(System.`in`)) {
    var sum = 0
    var input = readlnOrNull()
    while (!input.isNullOrEmpty()) {
        val number = input.toInt()

        if (abs(sum - 100) < abs(sum - 100 + number)) {
            break;
        }

        sum += number
        input = readlnOrNull()
    }

    println(sum)
}
