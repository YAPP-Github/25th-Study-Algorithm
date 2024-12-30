import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B11497 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int caseCount = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < caseCount; i++) {
            int treeCount = Integer.parseInt(br.readLine());
            // 각 케이스 별로 정렬
            int[] trees = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
            int[] treeLocation = new int[treeCount];
            int index = 0, left = 0, right = treeCount - 1;
            // 정렬된 나무들을 가지고 정해진 순서로 배치
            // 순서는 인덱스 기준으로 다음과 같음
            // 0 2 4 6 ... 7 5 3 1
            while (left <= right && index < treeCount) {
                treeLocation[left++] = trees[index++];
                if (index < treeCount) {
                    treeLocation[right--] = trees[index++];
                }
            }

            // 최대 차이를 구한다
            int maxDiff = 0;
            for (int j = 0; j < treeCount; j++) {
                maxDiff = Math.max(maxDiff, Math.abs(treeLocation[j] - treeLocation[(j + 1) % treeCount]));
            }
            sb.append(maxDiff).append("\n");
        }

        System.out.println(sb);
    }
}
