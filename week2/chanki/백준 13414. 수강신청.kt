import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val firstCommandSplit = readlnOrNull()!!.split(" ")
    var limitOfStudentNumber = firstCommandSplit[0].toInt()
    val applicationCount = firstCommandSplit[1].toInt()

    // 학생별 신청 횟수
    val countByApplicant = mutableMapOf<String, Int>()
    // 학생 지원 목록
    val applicants = mutableListOf<String>()
    for (i in 1..applicationCount) {
        readlnOrNull()!!.apply {
            applicants.add(this)
            countByApplicant.put(
                this,
                countByApplicant.getOrDefault(this, 0) + 1
            )
        }
    }

    val sb = StringBuilder()
    for (applicant in applicants) {
        // 지원자의 지원 횟수를 감소
        countByApplicant[applicant] = countByApplicant[applicant]!! - 1
        // 만약 지원한 이력이 아직 남아있다면 패스
        if (countByApplicant[applicant] != 0) {
            continue
        }

        sb.append(applicant).append("\n")
        // 만약 수강정원이 모두 찼으면 종료
        if (--limitOfStudentNumber == 0) {
            break
        }
    }

    println(sb)
}
