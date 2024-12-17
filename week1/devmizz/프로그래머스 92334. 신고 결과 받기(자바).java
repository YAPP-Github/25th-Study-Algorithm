import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class P92334 {

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] mailCount = new int[id_list.length];
        // 메일 보내는 횟수 카운트
        Map<String, Integer> mailCountById = new LinkedHashMap<>();
        // 피신고자 : Set(신고자)
        Map<String, Set<String>> reportTargetByReporters = new HashMap<>();

        // 피신고자 맵 초기화
        for (String r : report) {
            String[] split = r.split(" ");

            reportTargetByReporters.putIfAbsent(split[1], new HashSet<>());
            reportTargetByReporters.get(split[1]).add(split[0]);
        }

        // 피신고자 맵을 순회하며, 메일 보내는 횟수 기록
        for (Map.Entry<String, Set<String>> entry : reportTargetByReporters.entrySet()) {
            if (entry.getValue().size() >= k) {
                entry.getValue().forEach((it) -> {
                    mailCountById.put(it, mailCountById.getOrDefault(it, 0) + 1);
                });
            }
        }

        // 입력 순으로 횟수 기록
        for (int i = 0; i < id_list.length; i++) {
            mailCount[i] = mailCountById.getOrDefault(id_list[i], 0);
        }

        return mailCount;
    }
}