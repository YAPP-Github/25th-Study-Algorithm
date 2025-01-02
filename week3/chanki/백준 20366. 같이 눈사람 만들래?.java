import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 문제 풀이에 필요한 아이디어
// 눈덩이를 크기 순으로 정렬하고 A B C D 가 있다면
// 두 눈덩이 간 크기의 최소 차는 A, D / B, C로 구성될 때다.
public class B20366 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int snowballCount = Integer.parseInt(br.readLine());
        List<Integer> radiusOfSnowman = Arrays.stream(br.readLine().split(" "))
            .map(Integer::parseInt)
            .sorted()
            .collect(Collectors.toList());

        int minimumDiff = Integer.MAX_VALUE;

        outer:
        // 바깥 눈덩이를 가능한 케이스대로 모두 순회
        for (int outerLeft = 0; outerLeft < snowballCount - 3; outerLeft++) {
            for (int outerRight = snowballCount - 1; outerLeft + 2 < outerRight; outerRight--) {
                int innerLeft = outerLeft + 1;
                int innerRight = outerRight - 1;
                // 안쪽 눈덩이는 투포인터로 진행
                while (innerLeft < innerRight) {
                    int innerSnowmanSize = radiusOfSnowman.get(innerLeft) + radiusOfSnowman.get(innerRight);
                    int outerSnowmanSize = radiusOfSnowman.get(outerLeft) + radiusOfSnowman.get(outerRight);
                    int snowmanSizeDiff = outerSnowmanSize - innerSnowmanSize;

                    minimumDiff = Math.abs(minimumDiff) < Math.abs(snowmanSizeDiff)
                        ? minimumDiff
                        : snowmanSizeDiff;

                    // 차이가 0인 경우 더 작을 수는 없으므로 모든 순회를 종료
                    // outer - left > 0 이면 inner가 커져야 하므로, innerLeft++
                    if (snowmanSizeDiff == 0) {
                        break outer;
                    } else if (snowmanSizeDiff > 0) {
                        innerLeft++;
                    } else {
                        innerRight--;
                    }
                }
            }
        }

        System.out.println(Math.abs(minimumDiff));
    }
}
