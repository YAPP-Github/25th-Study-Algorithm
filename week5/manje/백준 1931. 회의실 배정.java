import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 회의 시간을 저장할 2차원 배열 [회의 개수][시작시간, 종료시간]
        int[][] t = new int[N][2];

        // N개의 회의 시작시간과 종료시간 입력
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            t[i][0] = Integer.parseInt(st.nextToken());
            t[i][1] = Integer.parseInt(st.nextToken());
        }

        // 회의를 종료시간 기준으로 정렬
        // 종료시간이 같은 경우 시작시간이 빠른 순으로 정렬
        Arrays.sort(t, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                  return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        
        int count = 0; // 선택된 회의의 개수
        int z = 0; // 이전 회의의 종료 시간

        // 현재 회의의 시작시간이 이전 회의의 종료시간보다 크거나 같으면
        // 현재 회의의 종료시간을 저장하고 선택된 회의 개수 증가
        for(int i = 0; i < N; i++){
            if(z <= t[i][0]) {
                z = t[i][1];
                count++;
            }
        }
        System.out.print(count);
    }
}
