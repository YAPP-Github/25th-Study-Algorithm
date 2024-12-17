import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PR2851 {

    public static void main(String[] args) throws IOException {

        // 첫번째 풀이 : 배열에 입력값을 저장하고 반복문으로 조건을 충족시키면 결과를 반환함
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int line = 10;
//        int[] scores = new int[line];
//
//        for (int i = 0; i < line; i++) {
//            scores[i] = Integer.parseInt(br.readLine());
//        }
//
//        int total = 0;
//        int before;
//
//        for (int score : scores) {
//            before = total;
//            total += score;
//
//            if (total > 100) {
//                int diff1 = 100 - before;
//                int diff2 = total - 100;
//
//                if (diff1 >= diff2) {
//                    System.out.println(total);
//                } else {
//                    System.out.println(before);
//                }
//                return;
//            }
//        }
//
//        System.out.println(total);

        // 2번째 풀이 : 누적합으로 입력값을 저장하고, 반복문을 이용하여 조건이 만족되면 결과를 반환함.
        // 메모리와 시간은 둘다 비슷함
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int line = 10;
        int[] scores = new int[line + 1];
        scores[0] = 0;

        for (int i = 1; i < scores.length; i++) {
            scores[i] = Integer.parseInt(reader.readLine()) + scores[i - 1];
        }

        for (int i = 2; i < scores.length; i++) {
            if (scores[i] >= 100) {
                int diff1 = scores[i] - 100;
                int diff2 = 100 - scores[i - 1];

                if (diff1 <= diff2) {
                    System.out.println(scores[i]);
                } else {
                    System.out.println(scores[i - 1]);
                }
                return;
            }
        }

        System.out.println(scores[line]);
    }
}
