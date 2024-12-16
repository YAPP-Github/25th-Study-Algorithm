import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B10830 {

    // 메모이제이션 (캐시 역할, 연산 기록 저장)
    static Map<Long, int[][]> memoization = new HashMap<>();

    static int[][] calculate(int[][] matrix, long pow) {
        if (pow == 1) {
            return matrix;
        }

        // 메모이제이션 확인
        if (memoization.containsKey(pow)) {
            return memoization.get(pow);
        }

        long half = pow / 2;

        // 분할 정복
        // 행렬은 결합법칙이 성립하므로, 거듭제곱에 대해서는 교환법칙도 성립
        int[][] matrixA = calculate(matrix, half);
        int[][] matrixB = calculate(matrix, pow - half);

        int[][] result = new int[matrixA.length][matrixA.length];
        // 행렬의 곱 진행
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int sum = 0;
                for (int k = 0; k < matrix.length; k++) {
                    sum += matrixA[i][k] * matrixB[k][j] % 1000;
                }
                result[i][j] = sum % 1000;
            }
        }

        // 메모이제이션 저장
        memoization.put(pow, result);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄 처리
        String[] splitFirstLine = br.readLine().split(" ");
        int size = Integer.parseInt(splitFirstLine[0]);
        long pow = Long.parseLong(splitFirstLine[1]);

        // 나머지 줄 처리 (행렬 입력)
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(split[j]);
            }
        }

        int[][] result = calculate(matrix, pow);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(result[i][j] % 1000).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
