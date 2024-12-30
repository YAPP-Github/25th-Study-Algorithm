import java.util.*
import kotlin.collections.ArrayDeque

// 자바와 동일하여 주석 생략
fun main() = with(Scanner(System.`in`)) {
    val formula = readlnOrNull()!!
    val deque = ArrayDeque<String>()
    for (c in formula) {
        deque.addLast(c.toString())
    }

    println(convertToPostfixFormula(removeBracket(deque)))
}

private fun removeBracket(deque: ArrayDeque<String>): ArrayDeque<String> {
    val result = ArrayDeque<String>()
    while (deque.isNotEmpty()) {
        val element = deque.removeFirst()
        when (element) {
            ")" -> {
                val bracketElements = ArrayDeque<String>()
                var bracketElement: String
                while ((result.removeLast().also { bracketElement = it }) != "(") {
                    bracketElements.addFirst(bracketElement)
                }
                result.addLast(convertToPostfixFormula(bracketElements))
            }
            else -> result.addLast(element)
        }
    }

    return result
}

private fun convertToPostfixFormula(deque: ArrayDeque<String>): String {
    val postfixMultiplyAndDivide = ArrayDeque<String>()
    while (deque.isNotEmpty()) {
        val element = deque.removeFirst()
        when {
            element == "*" || element == "/" -> {
                postfixMultiplyAndDivide.addLast(
                    postfixMultiplyAndDivide.removeLast() + deque.removeFirst() + element
                )
            }
            else -> postfixMultiplyAndDivide.addLast(element)
        }
    }

    val result = ArrayDeque<String>()
    while (postfixMultiplyAndDivide.isNotEmpty()) {
        val element = postfixMultiplyAndDivide.removeFirst()
        when {
            element == "+" || element == "-" -> {
                result.addLast(
                    result.removeLast() + postfixMultiplyAndDivide.removeFirst() + element
                )
            }
            else -> result.addLast(element)
        }
    }

    val sb = StringBuilder()
    for (s in result) {
        sb.append(s)
    }
    return sb.toString()
}