import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class B11286 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문제의 요구사항에 맞춰 힙 선언
        // 절댓값이 같으면 원본 값으로 비교
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            Comparator.<int[]>comparingInt(o -> o[0])
                .thenComparing(o -> o[1])
        );
        StringBuilder sb = new StringBuilder();
        Integer inputCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < inputCount; i++) {
            Integer input = Integer.parseInt(br.readLine());
            // 잘댓값과 원본 함께 저장
            if (input != 0) {
                pq.add(new int[]{Math.abs(input), input});
                continue;
            }

            if (pq.isEmpty()) {
                sb.append("0").append("\n");
                continue;
            }

            sb.append(pq.poll()[1]).append("\n");
        }

        System.out.println(sb);
    }
}
