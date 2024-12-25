import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BOJ-13414: 수강신청
// 1. 수강신청 활성화 되면, 순서대로 학생 대기목록에 추가
// 2. 이미 대기열에 있는데 누르면 맨 뒤로 밀려남
// 3. 비활성화되면, 순서대로 수강신청 완료
public class BOJ_13414 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Set<String> set = new LinkedHashSet<>();
        for (int i=0; i<L; i++) {
            String id = br.readLine();
            if (set.contains(id)) {
                set.remove(id);
            }
            set.add(id);
        }

        int cnt = 0;
        for (String id : set) {
            System.out.println(id);
            cnt++;
            if (cnt == K) break;
        }
    }
}
