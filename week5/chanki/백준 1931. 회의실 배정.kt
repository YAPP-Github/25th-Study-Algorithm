import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    data class Meeting(
        val start: Long,
        val end: Long
    )

    val numberOfMeeting = readLine().toInt()
    val pq = PriorityQueue(
        Comparator.comparingLong<Meeting> { it.end }
            .thenComparingLong { it.start }
    )
    repeat(numberOfMeeting) {
        pq.add(
            readLine().split(" ").let { Meeting(it[0].toLong(), it[1].toLong()) }
        )
    }

    var result = 0
    var lastMeetingEndAt = 0L
    while (pq.isNotEmpty()) {
        val meeting = pq.poll()
        if (meeting.start < lastMeetingEndAt) {
            continue
        }

        lastMeetingEndAt = meeting.end
        result++
    }

    println(result)
}