import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ-20366: 같이 눈사람 만들래?
// 언니와 동생에게 N개의 눈덩이가 있음, 지름은 Hi
// 눈사람은 두 눈덩이로 구성되며, 위 눈덩이는 아래보다 크면 안됨
// 눈사람의 키 차이가 작을 수록 좋은 것
public class BOJ_20366 {
    static int N;
    static int[] H;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = new int[N];
        for (int i=0; i<N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(H);

        // 투포인터
        // 첫번째 눈사람 빙빙
        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {

                int s = 0; int e = N-1;
                // 두번째 눈사람 빙빙
                while (s<e) {
                    // 선택한 눈덩이는 제외
                    if (s==i || s==j) {
                        s++;
                        continue;
                    }
                    if (e==i || e==j) {
                        e--;
                        continue;
                    }

                    answer = Math.min(answer, Math.abs((H[i]+H[j]) - (H[s]+H[e])));
                    // 2번째 눈사람 크기 줄임
                    if (H[i]+H[j] < H[s]+H[e]) {
                        e--;
                    }
                    // 2번째 눈사람 크기 늘림
                    else {
                        s++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
