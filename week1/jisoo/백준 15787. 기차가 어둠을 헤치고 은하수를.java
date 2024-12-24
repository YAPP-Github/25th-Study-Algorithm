import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N개 기차가 은하수 건넘
// 20개 좌석이 있음
public class BOJ_15787 {
    static int N, M;
    static int[] trains;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        trains = new int[N];

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            if (cmd==1) {
                // 1. 사람을 태움 -> 해당 좌석을 1로 바꿈
                int x = Integer.parseInt(st.nextToken());
                trains[idx-1] |= (1<<x);
            } else if (cmd==2) {
                // 2. 사람을 내림 -> 해당 좌석을 0으로 바꿈
                int x = Integer.parseInt(st.nextToken());
                trains[idx-1] &= ~(1<<x);
            } else if (cmd==3) {
                // 3. 좌석을 한 칸씩 뒤로 옮김
                trains[idx-1] <<= 1;
                trains[idx-1] &= ((1<<21)-1);
            } else if (cmd==4) {
                // 4. 좌석을 한 칸씩 앞으로 옮김
                trains[idx-1] >>= 1;
                trains[idx-1] &= ~1;
            }
        }

        boolean[] visited = new boolean[1<<21];
        int answer = 0;
        for (int i=0; i<N; i++) {
            if (!visited[trains[i]]) {
                visited[trains[i]] = true;
                answer++;
            }
        }

        System.out.println(answer);
    }
}
