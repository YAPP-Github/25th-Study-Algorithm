import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// BOJ-1931: 회의실 배정
// N개 회의에 대해 사용푤르 만듦
// 각 회의가 겹치지 않게 사용할 수 있는 회의의 최대 개수는?
public class BOJ_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] timeTable = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            timeTable[i][0] = Integer.parseInt(st.nextToken());
            timeTable[i][1] = Integer.parseInt(st.nextToken());
        }

        // 끝나는 시간 기준으로 정렬
        Arrays.sort(timeTable, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1]==o2[1]) {
                    return o1[0]-o2[0];
                } else {
                    return o1[1]-o2[1];
                }
            }
        });

        int answer = 0;
        int temp = 0; // 이전 종료 시간
        for (int i=0; i<N; i++) {
            // 다음 회의 가능한 경우
            if (temp <= timeTable[i][0]) {
                temp = timeTable[i][1];
                answer++;
            }
        }
        System.out.println(answer);
    }
}
