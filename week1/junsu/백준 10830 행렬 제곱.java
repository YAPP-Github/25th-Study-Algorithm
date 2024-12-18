import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PR10830 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int[][] A = new int[N][N];

        // 배열 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 결과 생성
        int[][] result = power(A, B);

        //결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // 짝수일 떄와 홀수일 때를 분할해서 거듭제곱을 실행함
    // A^5 = A * A^4
    // A^8 = A^4 * A^4
    private static int[][] power(int[][] matrix, long exp) {
        int[][] result = identity();
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = multiple(result, matrix);
            }
            matrix = multiple(matrix, matrix);
            exp /= 2;
        }

        return result;
    }

    // 단일 행렬 생성
    private static int[][] identity() {
        int[][] id = new int[N][N];
        for (int i = 0; i < N; i++) {
            id[i][i] = 1;
        }

        return id;
    }

    // 행렬의 제곱 실행
    private static int[][] multiple(int[][] matrix, int[][] matrix2) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += matrix[i][k] * matrix2[k][j];
                    result[i][j] %= 1000;
                }
            }
        }

        return result;
    }

}
