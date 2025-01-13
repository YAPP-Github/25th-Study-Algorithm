
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PR31423 {

    private static int N, left, right;
    private static String[] schools;
    private static int[] next;
    private static int[] tail;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        schools = new String[N + 1];
        next = new int[N + 1];
        tail = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            schools[i] = br.readLine();
            next[i] = i;
            tail[i] = i;
        }

        // 연결 리스트를 만들어 준다.
        int cur = -1;
        for(int i = 1; i <= N - 1; i++) {
            String[] line = br.readLine().split(" ");
            left = Integer.parseInt(line[0]);
            right = Integer.parseInt(line[1]);

            next[tail[left]] = right;
            tail[left] = tail[right];
            cur = left;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(schools[cur]);
            cur = next[cur];
        }

        System.out.println(sb.toString());
    }

}
