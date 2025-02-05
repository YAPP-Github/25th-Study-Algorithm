import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class B1939 {

    static class Move {
        int island;
        int weightLimit;

        public Move(int island, int weightLimit) {
            this.island = island;
            this.weightLimit = weightLimit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineInputs = br.readLine().split(" ");
        int numberOfIsland = Integer.parseInt(firstLineInputs[0]);
        int numberOfIslandInformation = Integer.parseInt(firstLineInputs[1]);

        // 인접리스트
        // 인접행렬(n*n)의 경우 두 가지 문제가 있음
        // 1. 기본적으로 메모리를 많이 잡아먹음
        // 2. 이동할 대상을 찾을 때 모든 행을 순회해야 함
        ArrayList<Move>[] weightLimitBetweenBridges = new ArrayList[numberOfIsland + 1];
        for (int i = 0; i < numberOfIsland; i++) {
            weightLimitBetweenBridges[i] = new ArrayList<>();
        }

        int minWeight = Integer.MAX_VALUE;
        int maxWeight = 0;

        for (int i = 0; i < numberOfIslandInformation; i++) {
            String[] bridgeInformation = br.readLine().split(" ");
            int islandA = Integer.parseInt(bridgeInformation[0]) - 1;
            int islandB = Integer.parseInt(bridgeInformation[1]) - 1;
            int weightLimit = Integer.parseInt(bridgeInformation[2]);

            minWeight = Math.min(minWeight, weightLimit);
            maxWeight = Math.max(maxWeight, weightLimit);

            weightLimitBetweenBridges[islandA].add(new Move(islandB, weightLimit));
            weightLimitBetweenBridges[islandB].add(new Move(islandA, weightLimit));
        }

        String[] problemInputs = br.readLine().split(" ");
        int departure = Integer.parseInt(problemInputs[0]) - 1;
        int destination = Integer.parseInt(problemInputs[1]) - 1;

        int left = minWeight;
        int right = maxWeight;
        int shippingWeight;

        // 중량제한의 최대값 저장
        int result = 0;
        // 중량을 먼저 결정해놓기 위한 이분탐색
        while (left <= right) {
            shippingWeight = left + (right - left) / 2;

            // 목적지 도달 여부
            boolean canReach = false;
            // 방문한 좌표 기록
            Set<Integer> alreadyVisited = new HashSet<>();
            // BFS를 위한 deque
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(departure);
            alreadyVisited.add(departure);

            while (!deque.isEmpty() && !canReach) {
                int now = deque.poll();
                List<Move> ends = weightLimitBetweenBridges[now];

                for (int i = 0; i < ends.size(); i++) {
                    // 다음 이동이 불가능한 경우 제외
                    Move end = weightLimitBetweenBridges[now].get(i);
                    int nextWeightLimit = end.weightLimit;
                    if (nextWeightLimit == 0 || alreadyVisited.contains(end.island) || nextWeightLimit < shippingWeight) {
                        continue;
                    }

                    // 이동이 가능한 경우 중
                    // 다음 행선지가 도착지인 경우
                    if (end.island == destination) {
                        canReach = true;
                        break;
                    }

                    // 도착지 아닌 경우
                    deque.add(end.island);
                    alreadyVisited.add(end.island);
                }
            }

            // 도달이 가능했다면, 중량제한을 더 늘려보면서 진행
            // 최대 중량제한을 기억해야 하므로 result에 저장
            if (canReach) {
                left = shippingWeight + 1;
                result = Math.max(result, shippingWeight);
            } else {
                right = shippingWeight - 1;
            }
        }

        System.out.println(result);
    }
}
