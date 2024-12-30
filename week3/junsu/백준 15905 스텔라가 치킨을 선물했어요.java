import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PR15905{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] scores = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int solved = Integer.parseInt(st.nextToken());
            int penalty = Integer.parseInt(st.nextToken());

            scores[i][0] = solved;
            scores[i][1] = penalty;
        }

        Arrays.sort(scores, (o1, o2) -> {
            if(o2[0] == o1[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        int result = 0;
        int fifth = scores[4][0];

        for(int i = 5; i < N; i++) {
            if(scores[i][0] == fifth) {
                result++;
            }
        }

        System.out.println(result);
    }

}
