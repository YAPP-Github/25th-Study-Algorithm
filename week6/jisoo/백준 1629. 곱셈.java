import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ-1629: 곱셈
// 자연수 A를 B번 곱하고 C로 나눈 나머지 출력
// ((A^5)*(A^5)*A)%C
// -> ((A^2)*(A^2)*A)%C
public class BOJ_1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());
        long C = Integer.parseInt(st.nextToken());

        System.out.println(pow(A, B, C));
    }

    private static long pow(long A, long B, long C) {
        if (B==1) return A%C;

        long temp = pow(A, B/2, C);
        if (B%2 == 0) {
            return (temp * temp) % C;
        }
        else {
            return ((temp * temp) % C * A) % C;
        }
    }
}
