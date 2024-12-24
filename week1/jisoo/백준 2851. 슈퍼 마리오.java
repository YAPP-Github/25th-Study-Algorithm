// 10개 버섯 놓임
// 순서대로 집고, 중간에 먹는 것도 중단 가능
// 받은 점수의 합을 최대한 100에 가깝게
// 마리오가 받는 점수는?
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2851 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = 0;

        for (int i=0; i<10; i++) {
            st = new StringTokenizer(reader.readLine());
            int num = Integer.parseInt(st.nextToken());
            if (Math.abs(answer+num-100) <= Math.abs(answer-100)) {
                answer += num;
            } else {
                break;
            }
        }

        System.out.println(answer);
    }
}
