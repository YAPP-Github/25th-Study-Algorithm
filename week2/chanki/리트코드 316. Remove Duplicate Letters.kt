fun removeDuplicateLetters(s: String): String {
    val seen = mutableMapOf<Char, Boolean>()
    val counter = mutableMapOf<Char, Int>()
    val stack = ArrayDeque<Char>()

    s.forEach { ch ->
        seen[ch] = false
        // 남은 문자 개수 기록
        counter[ch] = counter.getOrDefault(ch, 0) + 1
    }

    for (ch in s) {
        // 결과에 존재하는 경우 잔여 개수 감소시키고 패스
        if (seen[ch] == true) {
            counter[ch] = counter[ch]!! - 1
            continue
        }

        // 결과에 존재하지 않는 경우 사전식 순서로 결과를 저장하기 위해
        // 결과의 마지막 문자가 사전식으로 뒷순서 & 잔여개수 남아있으면(뒷순서에 또 나옴) 결과에서 제거
        while (stack.size > 0 && ch < stack.last()) {
            if (counter[stack.last()]!! > 0) {
                seen[stack.removeLast()] = false
            } else {
                break
            }
        }

        // 결과에 진행 중이던 문자를 저장
        stack.addLast(ch)
        seen[ch] = true
        counter[ch] = counter[ch]!! - 1
    }

    val sb = StringBuilder()
    while (stack.isNotEmpty()) {
        sb.append(stack.removeLast())
    }
    return sb.reverse().toString()
}