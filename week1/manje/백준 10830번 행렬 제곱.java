/*
    행렬(A)의 지수(B)를 절반으로 계속 분할 후 마지막에 A^1끼리 전부 곱하기
    ex1. 지수가 짝수일 경우) A^4 = A^2 * A^2 = (A^1 * A^1) * (A^1 * A^1)
    ex2. 지수가 홀수일 경우) A^5 = (A^2 * A^2) * A^1 = ((A^1 * A^1) * (A^1 * A^1)) * A^1 << 마지막에 A^1을 한 번 더 곱해준다.
*/
import java.util.*;
import java.io.*;

public class Main {
    static int[][] A;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken()); // 최대 1000억이므로 long
        A = new int[N][N]; // 초기 행렬 A
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                // 지수가 1일 땐 바로 리턴되므로 첫 행렬에서 1000이상인 원소는 모듈러 연산이 안 됨
                // 따라서 초기에 1000으로 나눈 나머지 값으로 초기화
                A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }			
        }
        int[][] result = pow(A, B);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
  
    // 행렬(A)의 B제곱 구하는 메서드
    public static int[][] pow(int[][] A, long exponent) {
        // 지수가 1이면 리턴
        if(exponent == 1L) {
            return A;
        }
        // 지수를 절반으로 분할 후 재귀호출하여 최종적으론 A^1끼리 전부 곱하기
        int[][] ret = pow(A, exponent / 2);
        ret = multiply(ret, ret);
        // 지수의 나머지가 홀수일 경우 A^1을 한 번 더 곱하기
        if(exponent % 2 == 1L) {
            ret = multiply(ret, A);
        }
        return ret;
    }
  
    // 두 행렬을 곱하는 메서드
    public static int[][] multiply(int[][] o1, int[][] o2) {
        int[][] ret = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    ret[i][j] += o1[i][k] * o2[k][j];
                    ret[i][j] %= 1000;
                }
            }
        }
        return ret;
    }
}
