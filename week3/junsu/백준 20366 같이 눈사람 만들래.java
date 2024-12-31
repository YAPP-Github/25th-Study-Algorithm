
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PR20366 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] diameters = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            diameters[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(diameters);

        int minDifference = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int left = 0;
                int right = N - 1;

                while (left < right) {
                    if(left == i || left == j) {
                        left++;
                        continue;
                    }

                    if(right == i || right == j) {
                        right--;
                        continue;
                    }

                    int firstSnowman = diameters[i] + diameters[j];
                    int secondSnowman = diameters[left] + diameters[right];
                    int difference = Math.abs(firstSnowman - secondSnowman);

                    minDifference = Math.min(minDifference, difference);

                    if (firstSnowman > secondSnowman) {
                        left++;
                    } else if(firstSnowman < secondSnowman) {
                        right--;
                    } else{
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(minDifference);
    }

}
