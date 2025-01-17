
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PR1931 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] times = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                times[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 강의 끝나는 시간을 오름차순 정렬
        Arrays.sort(times, (o1, o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });


        // 겹치지 않도록 계산
        int lastEnd = -1;
        int count = 0;
        for(int i = 0; i < N; i++) {
            int start = times[i][0];
            int end = times[i][1];

            if(start >= lastEnd) {
                lastEnd = end;
                count++;
            }
        }

        System.out.println(count);
    }

}
