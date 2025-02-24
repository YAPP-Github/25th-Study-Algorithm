import java.util.*;
import java.io.*;

public class Main {
    static int N;  // 눈덩이 수
    static int snows[];  // 눈덩이 배열
    static int min = Integer.MAX_VALUE;  // 최소 높이 차이
    static ArrayList<Snowman> snowmans;  // 눈사람 리스트
    
    static class Snowman implements Comparable<Snowman> {
        int headIdx;
        int bodyIdx;
        int height;

        public Snowman(int head, int body, int height) {
            this.headIdx = head;
            this.bodyIdx = body;
            this.height = height;
        }

        // 높이를 기준으로 눈사람 정렬
        @Override
        public int compareTo(Snowman o) {
            return this.height - o.height;
        }
    }
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 눈덩이 배열 초기화
        snows = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            snows[i] = Integer.parseInt(st.nextToken());
        }

        // 눈사람 리스트 초기화
        snowmans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                snowmans.add(new Snowman(i, j, snows[i] + snows[j]));
            }
        }

        // 눈사람 높이를 기준으로 정렬
        Collections.sort(snowmans);

        // 연속적인 눈사람 간의 높이 차이를 저장하는 리스트 초기화
        for (int i = 0; i < snowmans.size() - 1; i++) {
            Snowman snowman = snowmans.get(i);
            Snowman nextSnowman = snowmans.get(i + 1);

            int snow1 = snowman.bodyIdx;
            int snow2 = snowman.headIdx;
            int snow3 = nextSnowman.bodyIdx;
            int snow4 = nextSnowman.headIdx;

            // 두 눈사람이 겹치지 않을 때 높이 차이를 계산하여 최소값 갱신
            if (snow1 != snow3 && snow1 != snow4 && snow2 != snow3 && snow2 != snow4) {
                min = Math.min(min, nextSnowman.height - snowman.height);
            }
        }

        // 최소 높이 차이 출력
        System.out.print(min);
    }
}
