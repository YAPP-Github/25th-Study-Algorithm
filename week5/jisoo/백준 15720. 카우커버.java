import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ-15720: 카우버거
// 버거 1개, 사이드 1개, 음료 1개 선택하면 각각에 대해 10프로 할인
public class BOJ_15720 {
    static int B, C, D;
    static int[][] menu;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int size = Math.max(B, Math.max(C, D));
        menu = new int[3][size];

        int sum = 0;
        for (int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; st.hasMoreTokens(); j++) {
                menu[i][j] = Integer.parseInt(st.nextToken());
                sum += menu[i][j];
            }
        }

        // 정렬 후, 비싼 메뉴부터 세트를 만듦
        for (int i=0; i<3; i++) Arrays.sort(menu[i]);
        int discount = 0;
        for (int i=size-1; i>=0; i--) {
            int set = 0;
            boolean check = true;

            for (int j=0; j<3; j++) {
                set += menu[j][i];
                if (menu[j][i] == 0) {
                    check = false;
                }
            }
            discount += check ? set * 0.9 : set;
        }

        System.out.println(sum);
        System.out.println(discount);
    }
}
