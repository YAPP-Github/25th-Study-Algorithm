import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class PR13549 {

    private static int[] dx = {-1, 1};
    private static boolean[] location = new boolean[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // bfs로 최소시간 계산
        bfs(N, M);
    }

    private static void bfs(int n, int m) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{n, 0});
        location[n] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == m) {
                System.out.println(cur[1]);
                break;
            }

            // 텔레포트 사용
            int fast = cur[0] * 2;
            if (fast >= 0 && fast <= 100000 && !location[fast]) {
                queue.add(new int[]{fast, cur[1]});
                location[fast] = true;
            }

            // 좌우 이동
            for (int i = 0; i < 2; i++) {
                int next = cur[0] + dx[i];
                if (next >= 0 && next <= 100000 && !location[next]) {
                    queue.add(new int[]{next, cur[1] + 1});
                    location[next] = true;
                }
            }
        }
    }
}
