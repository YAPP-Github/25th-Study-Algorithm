import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class B18232 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineInput = br.readLine().split(" ");
        int numberOfPoint = Integer.parseInt(firstLineInput[0]);
        int numberOfTeleport = Integer.parseInt(firstLineInput[1]);

        String[] secondLineInput = br.readLine().split(" ");
        int start = Integer.parseInt(secondLineInput[0]);
        int end = Integer.parseInt(secondLineInput[1]);

        // 텔레포트 연결 정보
        Map<Integer, Set<Integer>> connectionMap = new HashMap<>();
        for (int i = 0; i < numberOfTeleport; i++) {
            String[] connectionInfo = br.readLine().split(" ");
            int p1 = Integer.parseInt(connectionInfo[0]);
            int p2 = Integer.parseInt(connectionInfo[1]);
            connectionMap.putIfAbsent(p1, new HashSet<>());
            connectionMap.get(p1).add(p2);
            connectionMap.putIfAbsent(p2, new HashSet<>());
            connectionMap.get(p2).add(p1);
        }

        int result = 0;
        // BFS를 위한 Deque
        Deque<Integer> deque = new ArrayDeque<>();
        // 방문 정보 저장
        boolean[] isVisited = new boolean[numberOfPoint + 1];
        deque.add(start);
        outer:
        while (!deque.isEmpty()) {
            int nextVisitWay = deque.size();
            // 매 방문 회차를 구분하기 위해, DFS 묶음 단위로 실행
            for (int i = 0; i < nextVisitWay; i++) {
                int now = deque.pop();
                isVisited[now] = true;

                // 다음 방문지에 end가 있으면 종료!
                if (now - 1 == end
                    || now + 1 == end
                    || (connectionMap.containsKey(now) && connectionMap.get(now).contains(end))
                ) {
                    result++;
                    break outer;
                }

                // -1로 이동
                if (0 < now - 1 && !isVisited[now - 1]) {
                    isVisited[now - 1] = true;
                    deque.add(now - 1);
                }
                // +1로 이동
                if (numberOfPoint >= now + 1 && !isVisited[now + 1]) {
                    isVisited[now + 1] = true;
                    deque.add(now + 1);
                }
                // 텔레포트 이동
                if (connectionMap.containsKey(now)) {
                    List<Integer> unAddedConnectionPoints = connectionMap.get(now)
                        .stream()
                        .filter(it -> !isVisited[it])
                        .collect(Collectors.toList());

                    deque.addAll(unAddedConnectionPoints);
                    for (int point : unAddedConnectionPoints) {
                        isVisited[point] = true;
                    }
                }
            }
            result++;
        }

        System.out.println(result);
    }
}
