import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int log = Integer.parseInt(br.readLine());
            int[] logs = new int[log];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < log; i++) {
                logs[i] = Integer.parseInt(st.nextToken());
            }

            // 오름차순 정렬
            Arrays.sort(logs);

            // 지그재그로 정렬
            int left = 0;
            int right = log - 1;
            int[] arranges = new int[log];
            for (int i = 0; i < log; i++) {
                if (i % 2 == 0) {
                    arranges[left++] = logs[i];
                } else {
                    arranges[right--] = logs[i];
                }
            }

            // 최대 간격 계산
            int maxLevel = 0;
            for (int i = 1; i < log; i++) {
                maxLevel = Math.max(maxLevel, Math.abs(arranges[i] - arranges[i - 1]));
            }

            // 처음과 마지막 간격 계산
            maxLevel = Math.max(maxLevel, Math.abs(arranges[log - 1] - arranges[0]));
            sb.append(maxLevel).append("\n");
        }

        System.out.println(sb);
    }
}
