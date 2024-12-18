import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PR14467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // MAP 자료구조를 이용하여 소가 이동했는지를 판단한다.
        Map<Integer, Integer> map = new HashMap<>();

        int count = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cow = Integer.parseInt(st.nextToken());
            int location = Integer.parseInt(st.nextToken());

            if (!map.containsKey(cow)) {
                map.put(cow, location);
            } else {
                if (map.get(cow) == location) {
                    continue;
                }

                if (map.get(cow) == 0) {
                    map.put(cow, 1);
                } else {
                    map.put(cow, 0);
                }
                count++;
            }
        }

        System.out.println(count);
    }

}
