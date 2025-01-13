import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BOJ-18232 텔레포트 정거장
// 주예는 S에 도착했지만 방주는 E에 있음
// 주예가 E로 이동
// 좌우로 이동하거나 X에 위치한 텔레포트 연결 지점으로 이동 가능
// 각 행동에는 1초가 소요
public class BOJ_18232 {
    static int N; static int M;
    static int S; static int E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
        S-=1; E-=1;

        ArrayList<ArrayList<Integer>> tel = new ArrayList<>();
        for (int i=0; i<N; ++i) tel.add(new ArrayList<>());
        for (int i=0; i<M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            tel.get(a).add(b);
            tel.get(b).add(a);
        }

        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[N];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[S] = 0;
        q.offer(S);

        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == E) break;

            for (int next: tel.get(v)) {
                // 최솟값 갱신
                if (visited[next] > visited[v]+1) {
                    visited[next] = visited[v]+1;
                    q.offer(next);
                }
            }

            if (v+1<N && visited[v+1] > visited[v]+1) {
                visited[v+1] = visited[v]+1;
                q.offer(v+1);
            }
            if (v-1>=0 && visited[v-1] > visited[v]+1) {
                visited[v-1] = visited[v]+1;
                q.offer(v-1);
            }
        }

        System.out.println(visited[E]);
    }
}
