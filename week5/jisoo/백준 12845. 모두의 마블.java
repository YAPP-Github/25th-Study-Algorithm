import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ-12845: 모두의 마블
// 카드 a에 카드 b를 덧붙일 수 있다
// 1. 두 카드는 인접한 카드여야 한다
// 2. 업그레이드 된 카드 a의 레벨은 변하지 않는다
// 합성할 때마다 두 카드 레벨의 합만큼 골드를 받는다
// 최대 골드를 받기 위해 카드를 합성하자
public class BOJ_12845 {
    static int N;
    static int[] L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = new int[N];
        for (int i = 0; i < N; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int max = 0;
        for (int i=0; i<N; i++) {
            answer += L[i];
            max = Math.max(max, L[i]);
        }

        answer += max * (N-2);
        System.out.println(answer);
    }
}
