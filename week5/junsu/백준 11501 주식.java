
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class PR11501 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            long maxProfit = 0;
            int maxPrice = 0;

            // 뒤에서부터 큰 주식을 갱신하면서 계산
            for(int k = N - 1; k >= 0; k--) {
                if(arr[k] > maxPrice) {
                    maxPrice = arr[k];
                } else {
                    maxProfit += maxPrice - arr[k];
                }
            }

            bw.append(maxProfit + "\n");
        }

        bw.flush();
    }

}
