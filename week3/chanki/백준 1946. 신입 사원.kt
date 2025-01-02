import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val sb = StringBuilder()
    repeat(readLine().toInt()) {
        val numberOfApplicant = readLine().toInt()
        val meetingGradeByPaperGrade = IntArray(numberOfApplicant + 1)

        // 지원자의 서류 점수를 index, 미팅 점수를 value로 하는 배열 초기화
        repeat(numberOfApplicant) {
            readLine().split(" ")
                .apply { meetingGradeByPaperGrade[this[0].toInt()] = this[1].toInt() }
        }

        var minimumMeetingGrade = Integer.MAX_VALUE
        var result = 0
        for (applicantPaperGrade in 1..numberOfApplicant) {
            if (meetingGradeByPaperGrade[applicantPaperGrade] < minimumMeetingGrade) {
                result++
                minimumMeetingGrade = meetingGradeByPaperGrade[applicantPaperGrade]
            }
        }

        sb.append(result).append("\n")
    }

    println(sb)
}
