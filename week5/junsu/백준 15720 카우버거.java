import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PR15720 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] bugger = new int[B];
        int[] side = new int[C];
        int[] beverage = new int[D];

        int total = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            int price = Integer.parseInt(st.nextToken());
            bugger[i] = price;
            total += price;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            int price = Integer.parseInt(st.nextToken());
            side[i] = price;
            total += price;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < D; i++) {
            int price = Integer.parseInt(st.nextToken());
            beverage[i] = price;
            total += price;
        }

        Arrays.sort(bugger);
        Arrays.sort(side);
        Arrays.sort(beverage);

        int setCount = Math.min(B, Math.min(C, D));

        // 정렬 후 최소 세트갯수만큼 할인을 함
        int disCountedTotal = 0;
        for(int i = 0; i < setCount; i++) {
            int setPrice = bugger[B - 1 - i] + side[C - 1 - i] + beverage[D - 1 - i];
            disCountedTotal += (int) (setPrice * 0.9);
        }

        // 남는 메뉴 합 추가
        for(int i = 0; i < B - setCount; i++) {
            disCountedTotal += bugger[i];
        }

        for(int i = 0; i < C - setCount; i++) {
            disCountedTotal += side[i];
        }

        for (int i = 0; i < D - setCount; i++) {
            disCountedTotal += beverage[i];
        }

        System.out.println(total);
        System.out.println(disCountedTotal);
    }

}
