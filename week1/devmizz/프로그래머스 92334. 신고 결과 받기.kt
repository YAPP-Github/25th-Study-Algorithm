class P92334Kt {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val mailCount = IntArray(id_list.size)
        // 메일 보내는 횟수 기록하는 맵
        val mailCountById = mutableMapOf<String, Int>()
        // 피신고자 : Set(신고자)
        val reportedIdByReporters = mutableMapOf<String, MutableSet<String>>()

        // 피신고자 : Set(신고자) 초기화
        for (r in report) {
            val split = r.split(" ")
            reportedIdByReporters.putIfAbsent(split[1], mutableSetOf())
            reportedIdByReporters[split[1]]?.add(split[0])
        }

        // 피신고자 맵을 순회하며 메일 보내는 횟수 기록
        for (entry in reportedIdByReporters.entries) {
            if (entry.value.size >= k) {
                entry.value.forEach {
                    mailCountById[it] = mailCountById.getOrDefault(it, 0) + 1
                }
            }
        }

        // 입력 순으로 메일 횟수 기록
        for (idIndex in id_list.indices) {
            mailCount[idIndex] = mailCountById.getOrDefault(id_list[idIndex], 0)
        }

        return mailCount
    }
}