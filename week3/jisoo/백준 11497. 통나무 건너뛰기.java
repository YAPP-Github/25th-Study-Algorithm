import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ-11497: 통나무 건너뛰기
// 한 줄에 주어진 통나무들로 만들 수 있는 최소 난이도
public class BOJ_11497 {
    static StringTokenizer st;
    static int N;
    static int[] L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-->0) {
            N = Integer.parseInt(br.readLine());
            L = new int[N];
            st = new StringTokenizer(br.readLine());

            for (int i=0; i<N; i++) {
                L[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println(sort());
        }
    }

    private static int sort() {
        Arrays.sort(L);
        int max = -1;
        for (int i=2; i<N; i++) {
            // 인접
            max = Math.max(max, Math.abs(L[i]-L[i-1]));
            // 건너뛰는
            max = Math.max(max, Math.abs(L[i]-L[i-2]));
        }
        return max;
    }
}
