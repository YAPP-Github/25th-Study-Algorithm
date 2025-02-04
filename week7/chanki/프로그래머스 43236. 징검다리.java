package programmers;

import java.util.Arrays;

public class P43236 {
    public int solution(int distance, int[] rocks, int n) {
        // 바위 사위 거리를 이분탐색
        int left = 0;
        int right = distance;
        int minDistBetweenRocks = right;

        int[] sortedRocks = Arrays.stream(rocks).sorted().toArray();
        int[] targets = new int[sortedRocks.length + 1];
        for (int i = 0; i < sortedRocks.length; i++) {
            targets[i] = sortedRocks[i];
        }
        targets[sortedRocks.length] = distance;

        int result = 0;
        while (left <= right) {
            // 바위들을 이동하면서, mid보다 간격이 짧은 경우 바위를 제거
            // 이동이 끝났을 때, 제거한 바위의 개수가
            // 1. n 미만 -> 더 제거해야 하므로, 간격을 좁힌다.
            // 2. n과 같음 -> 조건은 맞추었으나, 거리의 최소값을 구하기 위해 간격을 좁혀본다.
            // 3. n 초과 -> 너무 많이 제거한 것이므로, 간격을 다시 넓힌다.
            minDistBetweenRocks = left + (right - left) / 2;
            int sumOfRemovingRocks = 0;
            int movingDistance = 0;

            for (int i = 0; i < targets.length; i++) {
                if (canAcrossRock(movingDistance, targets[i], minDistBetweenRocks)) {
                    movingDistance = targets[i];
                } else {
                    sumOfRemovingRocks++;
                }
            }

            // 제거한 바위 개수가 n개 미만 -> 간격이 너무 좁다
            // 제거한 바위 개수가 n개 -> 이 중에서 거리의 최솟값이 최대가 되는 값을 기록
            if (sumOfRemovingRocks <= n) {
                left = minDistBetweenRocks + 1;
                result = Math.max(result, minDistBetweenRocks);
            } else {
                right = minDistBetweenRocks - 1;
            }
        }

        return result;
    }

    private boolean canAcrossRock(int now, int target, int minDist) {
        return target - now >= minDist;
    }
}
