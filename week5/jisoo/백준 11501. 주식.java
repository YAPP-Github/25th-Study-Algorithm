import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ-11501: 주식
// 1. 주식 하나를 산다
// 2. 원하는 만큼 가진 주식을 판다
// 3. 아무것도 안한다
// 최대 이익이 얼마나 되는지 계산
public class BOJ_11501 {
    static StringTokenizer st;
    static int N;
    static int[] stock;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-->0) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            stock = new int[N];
            for (int i = 0; i < N; i++) {
                stock[i] = Integer.parseInt(st.nextToken());
            }
            long max = 0;
            long answer = 0;

            for (int i=N-1; i>=0; i--) {
                if (stock[i]<=max) {
                    answer += max - stock[i];
                } else {
                    max = stock[i];
                }
            }
            System.out.println(answer);
        }
    }
}
