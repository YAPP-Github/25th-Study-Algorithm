import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class PR12845 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Integer[] cards = new Integer[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards, Collections.reverseOrder());

        int gold = 0;
        for(int i = 1; i < n; i++) {
            int level = cards[0];
            gold += level + cards[i];
        }

        System.out.println(gold);
    }

}
