import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B14467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int commandCount = Integer.parseInt(br.readLine());

        int answer = 0;
        Map<Integer, Integer> cowPosition = new HashMap<>();
        for (int i = 0; i < commandCount; i++) {
            String[] command = br.readLine().split(" ");
            int cow = Integer.parseInt(command[0]);
            int position = Integer.parseInt(command[1]);

            // 소의 이전 위치가 확인되어있고 이번 위치와 다르면 길을 건넌 것으로 간주한다.
            if (cowPosition.containsKey(cow) && cowPosition.get(cow) != position) {
                answer++;
            }

            // 현재 확인된 위치를 등록 혹은 수정한다.
            cowPosition.put(cow, position);
        }

        System.out.println(answer);
    }
}
