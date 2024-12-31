import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ-15905: 스텔라(STELLA)가 치킨을 선물했어요
// 1. 각 참가자는 해결한 문제 개수와 패널티 총합을 가짐
// 2. 해결한 문제 개수가 더 많은 참가자 순
// 3. 패널티 총합이 더 작은 참가자 순
public class BOJ_15905 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            arr[i][0] = p1; arr[i][1] = p2;
        }
        Arrays.sort(arr, (o1,o2) -> {
            if (o1[0]==o2[0]) {
                return o1[1]-o2[1];
            }
            return o2[0]-o1[0];
        });

        int pivot = arr[4][0];
        int cnt = 0;
        for (int i=5; i<N; i++) {
            if (pivot!=arr[i][0]) break;
            cnt++;
        }
        System.out.println(cnt);
    }
}
