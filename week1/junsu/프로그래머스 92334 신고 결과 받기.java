import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 신고자 : 피신고자들
        Map<String, List<String>> mail = new HashMap<>();

        // 피신고자 : 신고자들
        Map<String, List<String>> reported = new HashMap<>();

        for(int i = 0; i < id_list.length; i++) {
            mail.put(id_list[i], new LinkedList<>());
            reported.put(id_list[i], new LinkedList<>());
        }

        for(int i = 0; i < report.length; i++) {
            String[] complains = report[i].split(" ");
            String reporter = complains[0];
            String target = complains[1];

            List<String> targets = mail.get(reporter);

            if(!targets.contains(target)) {
                targets.add(target);
            }

            List<String> reporters = reported.get(target);

            if(!reporters.contains(reporter)) {
                reporters.add(reporter);
            }
        }

        // 정지된 사용자 생성
        List<String> stopUsers = new LinkedList<>();

        for(String target: reported.keySet()) {
            if(reported.get(target).size() >= k) {
                stopUsers.add(target);
            }
        }

        // 결과 반환
        int[] result = new int[id_list.length];
        for(int i = 0; i < id_list.length; i++) {
            int mailCount = 0;
            List<String> targets = mail.get(id_list[i]);

            for(String stopUser : stopUsers) {
                if(targets.contains(stopUser)) {
                    mailCount++;
                }
            }

            result[i] = mailCount;
        }

        return result;
    }
}