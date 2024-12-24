import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 소의 위치를 n번 관찰
// 최소 몇 번 길을 건넜는지 알아보자
public class BOJ_14467 {
    static int N;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int[] arr = new int[11];
        Arrays.fill(arr, -1);

        int answer = 0;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());

            if (arr[idx] == -1) {
                arr[idx] = pos;
            } else {
                if (arr[idx] != pos) {
                    arr[idx] = pos;
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}
