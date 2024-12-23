
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PR13414 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < L; i++) {
            String studentNumber = br.readLine();

            if (map.containsKey(studentNumber)) {
                map.remove(studentNumber);
            }

            map.put(studentNumber, 0);
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String studentNumber : map.keySet()) {
            sb.append(studentNumber).append("\n");
            count++;

            if(count == K) break;
        }

        System.out.println(sb.toString());
    }

}
