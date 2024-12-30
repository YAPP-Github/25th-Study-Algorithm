import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;

public class B15905 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 우선순위큐를 이용한 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0])
                return o1[1] - o2[1];
            return o2[0] - o1[0];
        });

        int attendanceCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < attendanceCount; i++) {
            String[] performanceInfo = br.readLine().split(" ");
            // 우선순위큐 삽입
            pq.add(new int[]{Integer.parseInt(performanceInfo[0]), Integer.parseInt(performanceInfo[1])});
        }

        int solvingProblemCount = 0;
        for (int i = 0; i < 5; i++) {
            // 5개 삭제 & 5등의 문제 풀이 수 저장
            solvingProblemCount = Objects.requireNonNull(pq.poll())[0];
        }

        int result = 0;
        // 우선순위큐가 비어있지 않고, 가장 최상단 원소의 문제풀이 수가 동일하다면 +1
        while (!pq.isEmpty() && pq.peek()[0] == solvingProblemCount) {
            result++;
            pq.poll();
        }

        System.out.println(result);
    }
}
