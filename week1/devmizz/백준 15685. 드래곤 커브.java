import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class B15685 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int dragonCurveCount = Integer.parseInt(br.readLine());

        // 입력값 초기화
        int[][] dragonCurveInputs = new int[dragonCurveCount][4];
        for (int i = 0; i < dragonCurveCount; i++) {
            dragonCurveInputs[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        // 전체 좌표에 각 dragonCurve 표시
        int[][] grid = new int[101][101];
        for (int[] input : dragonCurveInputs) {
            drawDragonCurve(grid, input[0], input[1], input[2], input[3]);
        }

        // 1*1 정사각형 개수 카운트
        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (grid[i][j] == 1 && grid[i + 1][j] == 1 && grid[i][j + 1] == 1 && grid[i + 1][j + 1] == 1) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    private static void drawDragonCurve(int[][] grid, int startX, int startY, int direction, int generation) {
        // 0세대 작업
        int endX = startX, endY = startY;
        if (direction % 2 == 0) {
            endX += 1 - direction;
        } else {
            endY += direction - 2;
        }
        grid[startY][startX] = 1;
        grid[endY][endX] = 1;

        // grid 전체를 순회하지 않는다.
        // 이미 마킹이 된 좌표만 회전시킬 수 있도록 한다.
        // components: dragonCurve 점들의 모음
        Set<List<Integer>> components = new HashSet<>();
        List<Integer> start = Arrays.asList(startX, startY);
        components.add(start);
        components.add(Arrays.asList(endX, endY));

        // curveComponents: 다음 세대에서 처리해야 하는 점들의 모음
        Deque<List<Integer>> curveComponents = new ArrayDeque<>(components);
        for (int i = 1; i <= generation; i++) {
            while (!curveComponents.isEmpty()) {
                List<Integer> rotatedComponent = rotateCoordinate(curveComponents.pop(), endX, endY);
                components.add(rotatedComponent);
                grid[rotatedComponent.get(1)][rotatedComponent.get(0)] = 1;
            }
            // 다음 end 좌표 초기화
            List<Integer> newEnd = rotateCoordinate(start, endX, endY);
            endX = newEnd.get(0);
            endY = newEnd.get(1);
            curveComponents.addAll(components);
        }
    }

    private static List<Integer> rotateCoordinate(List<Integer> before, int endX, int endY) {
        // 기준점을 기준으로 90도 회전
        // 원래 좌표평면과 다르다는 점을 반영
        // 기존 좌표평면계: (before.get(1) - endY + endX, endX - before.get(0) + endY)
        return Arrays.asList(endY - before.get(1) + endX, before.get(0) - endX + endY);
    }
}
