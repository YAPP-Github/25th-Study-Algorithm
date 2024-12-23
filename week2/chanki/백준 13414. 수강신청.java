import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class B13414 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstCommandSplit = br.readLine().split(" ");
        int limitNumberOfClassStudent = Integer.parseInt(firstCommandSplit[0]);
        int inputCount = Integer.parseInt(firstCommandSplit[1]);

        // 학번별 개수 카운팅을 위한 Map
        Map<String, Integer> studentIdentifierCount = new HashMap<>();
        // 입력 순서 저장하는 Deque (List여도 상관 없음)
        Deque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < inputCount; i++) {
            String studentIdentifier = br.readLine();
            studentIdentifierCount.put(
                studentIdentifier,
                studentIdentifierCount.getOrDefault(studentIdentifier, 0) + 1
            );
            deque.add(studentIdentifier);
        }

        StringBuilder sb = new StringBuilder();
        // 큐가 비거나 수강정원이 채워질 때까지
        while (!deque.isEmpty() && limitNumberOfClassStudent > 0) {
            String studentIdentifier = deque.pop();
            Integer duplicateCountByStudentIdentifier = studentIdentifierCount.get(studentIdentifier);
            studentIdentifierCount.put(studentIdentifier, duplicateCountByStudentIdentifier - 1);

            // Deque에 중복 학번이 1개 이상 남아있으면 패스
            if (duplicateCountByStudentIdentifier > 1) {
                continue;
            }

            sb.append(studentIdentifier).append("\n");
            limitNumberOfClassStudent--;
        }

        System.out.println(sb);
    }
}
