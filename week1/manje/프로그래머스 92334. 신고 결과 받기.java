import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> reportList = new HashMap<>(); // 피신고자 별 신고자 목록
        Map<String, Integer> idToIndex = new HashMap<>(); // id로 인덱스 찾는 맵

        // idToIndex 초기화
        for(int i = 0; i < id_list.length; i++){
            idToIndex.put(id_list[i], i);
        }
        
        // reportList 초기화
        for (String id : id_list) {
            reportList.put(id, new HashSet<>());
        }
        
        // reportList에 신고 정보 저장
        for (String r : report) {
            StringTokenizer st = new StringTokenizer(r);
            String reporter = st.nextToken(); // 신고자
            String respondent = st.nextToken(); // 피신고자
            reportList.get(respondent).add(reporter); // 피신고자에 대한 신고자 목록에 신고자 추가
        }

        // 결과 계산
        for (String id : id_list) {
            if (reportList.get(id).size() >= k) { // 신고 횟수가 k 이상인 경우
                for (String reporter : reportList.get(id)) { // 해당 사용자를 신고한 사람들을 찾아서
                     answer[idToIndex.get(reporter)]++;
                }
            }
        }
        return answer;
    }
}
