import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// A의 B제곱 구하기 -> 여기서 1000으로 나눈 나머지 출력
public class BOJ_10830 {
    static StringTokenizer st;
    static int[][] matrix;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken())%1000;
            }
        }

        int[][] result = pow(matrix, B);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static int[][] pow(int[][] matrix, long B) {
        if (B == 1L) {
            return matrix;
        }

        // 지수를 절반으로 분할해 재귀호출
        int[][] temp = pow(matrix, B/2);

        if (B%2 == 0) {
            return multiply(temp, temp);
        } else {
            return multiply(multiply(temp, temp), matrix);
        }
    }

    public static int[][] multiply(int[][] o1, int[][] o2) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += o1[i][k] * o2[k][j];
                    result[i][j] %= 1000;
                }
            }
        }

        return result;
    }
}
