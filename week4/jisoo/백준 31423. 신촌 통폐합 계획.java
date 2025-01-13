import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ-31423: 신촌 통폐합 계획
public class BOJ_31423 {
    public static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] univ = new String[N+1];
        int[] next = new int[N+1];
        int[] tail = new int[N+1];

        for (int i=1; i<=N; i++) {
            univ[i] = br.readLine();
            tail[i] = i;
        }
        for (int i=1; i<N; i++) {
            String[] part = br.readLine().split(" ");
            M = Integer.parseInt(part[0]);
            K = Integer.parseInt(part[1]);
            next[tail[M]] = K;
            tail[M] = tail[K];
        }

        StringBuilder sb = new StringBuilder();
        while (M!=0) {
            sb.append(univ[M]);
            M = next[M];
        }
        System.out.println(sb);
    }
}
