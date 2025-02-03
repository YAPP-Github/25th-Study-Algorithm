import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ-6236: 용돈 관리
// N일 동안 M번만 돈을 뺌
// K원 인출해서 쓸 수 있다면 쓰고, 모자라면 남은 금액은 넣고 다시 K원 인출
// 정확히 M번을 맞추기 위해 남은 금액이 사용할 금액보다 많더라도 남은 금액은 넣고 다시 K원 인출 가능
// 최소 K원을 구하라
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
        int[] days = new int[N];
        int start=1, end=1;
        for (int i = 0; i < N; i++) {
            days[i] = Integer.parseInt(br.readLine());
            start = Math.max(start, days[i]);
            end += days[i];
        }

        while (start <= end) {
            int mid = (start+end)/2;

            int cnt = 1;
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += days[i];
                if (sum > mid) {
                    cnt++;
                    sum = days[i];
                }
            }

            if (cnt <= M) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }
}
