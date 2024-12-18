import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class PR15787 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trains = new int[N + 1];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int trainIdx = Integer.parseInt(st.nextToken());

            // 명령어에 따라 비트 마스크로 위치를 바꾼다.
            switch (command) {
                case 1:
                    int seatToIn = Integer.parseInt(st.nextToken());
                    trains[trainIdx] |= (1 << (seatToIn - 1));
                    break;
                case 2:
                    int seatToOut = Integer.parseInt(st.nextToken());
                    trains[trainIdx] &= ~(1 << (seatToOut - 1));
                    break;
                case 3:
                    trains[trainIdx] <<= 1;
                    trains[trainIdx] &= (1 << 20) - 1;
                    break;
                case 4:
                    trains[trainIdx] >>= 1;
                    break;
            }
        }

        // set을 사용해서 중복 없이 저장한다.
        for (int i = 1; i <= N; i++) {
            set.add(trains[i]);
        }

        System.out.println(set.size());
    }

}
