
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PR1946 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            int[][] scores = new int[N][2];
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                while (st.hasMoreTokens()) {
                    int documentScore = Integer.parseInt(st.nextToken());
                    int interviewScore = Integer.parseInt(st.nextToken());

                    scores[j][0] = documentScore;
                    scores[j][1] = interviewScore;
                }
            }

            // 서류 별 오름차순 정렬
            Arrays.sort(scores, (a, b) -> a[0] - b[0]);

            int minInterviewScore = Integer.MAX_VALUE;
            int result = 0;
            // 면접 항목 가장 낮은 순 업데이트
            for(int k = 0; k < N; k++) {
                if(scores[k][1] < minInterviewScore) {
                    result++;
                    minInterviewScore = scores[k][1];
                }
            }

            bw.append(String.valueOf(result)).append("\n");
        }

        bw.flush();
    }
}
