
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class PR18232 {

    private static boolean[] visited;
    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int[] move = {-1, 1};
    private static int N, M, S, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int dot1 = Integer.parseInt(st.nextToken());
            int dot2 = Integer.parseInt(st.nextToken());

            map.get(dot1).add(dot2);
            map.get(dot2).add(dot1);
        }

        // 최소 시간이므로 BFS를 사용함
        bfs(S);
    }

    private static void bfs(int s) {
        Queue<int[]> pq = new ArrayDeque<>();
        pq.offer(new int[]{s, 0});
        visited[s] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == E) {
                System.out.println(cur[1]);
                return;
            }

            // 좌우로 움직일 때
            for (int i = 0; i < move.length; i++) {
                int next = cur[0] + move[i];
                if (next >= 1 && next <= N && !visited[next]) {
                    pq.offer(new int[]{next, cur[1] + 1});
                    visited[next] = true;
                }
            }

            // 정거장 텔레포트를 사용할 떄
            for (int next : map.get(cur[0])) {
                if (!visited[next]) {
                    pq.offer(new int[]{next, cur[1] + 1});
                    visited[next] = true;
                }
            }
        }
    }


}
