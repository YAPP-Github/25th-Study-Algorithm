import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class B15681 {

    // 노드별 자식 노드 개수 저장하는 배열
    static int[] numberOfChildNode;

    // 노드 클래스 선언
    static class B15681Node {
        Integer value;
        List<B15681Node> children;

        public B15681Node(Integer value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstCommandSplit = br.readLine().split(" ");

        int numberOfNode = Integer.parseInt(firstCommandSplit[0]);
        int rootNodeValue = Integer.parseInt(firstCommandSplit[1]);
        int numberOfQuery = Integer.parseInt(firstCommandSplit[2]);

        numberOfChildNode = new int[numberOfNode + 1];

        // 간선 정보 초기화
        Map<Integer, List<Integer>> edgeInfo = new HashMap<>();
        for (int i = 0; i < numberOfNode - 1; i++) {
            String[] connection = br.readLine().split(" ");
            int firstValue = Integer.parseInt(connection[0]);
            int secondValue = Integer.parseInt(connection[1]);
            edgeInfo.putIfAbsent(firstValue, new ArrayList<>());
            edgeInfo.putIfAbsent(secondValue, new ArrayList<>());
            edgeInfo.get(firstValue).add(secondValue);
            edgeInfo.get(secondValue).add(firstValue);
        }

        // 트리 초기화
        Deque<B15681Node> deque = new ArrayDeque<>();
        // 방문 기록 저장
        Set<Integer> alreadyVisited = new HashSet<>();
        B15681Node rootNode = new B15681Node(rootNodeValue);
        deque.add(rootNode);
        alreadyVisited.add(rootNodeValue);
        // BFS
        while (!deque.isEmpty()) {
            B15681Node parent = deque.poll();
            for (Integer value : edgeInfo.get(parent.value)) {
                // 연결된 간선 정보 중 부모는 제와
                if (alreadyVisited.contains(value))
                    continue;

                B15681Node child = new B15681Node(value);
                parent.children.add(child);
                deque.add(child);
                alreadyVisited.add(value);
            }
        }

        // 자식들을 순회하며 자식 노드들 합계 저장
        countChildren(rootNode);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfQuery; i++) {
            sb.append(numberOfChildNode[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

    private static int countChildren(B15681Node node) {
        // 자식이 비어있으면 1 반환
        if (node.children.isEmpty()) {
            numberOfChildNode[node.value] = 1;
            return 1;
        }

        int count = 1;
        for (B15681Node child : node.children) {
            // 자식들을 순회하면서, 각 자식 노드 개수를 합산
            count += countChildren(child);
        }
        numberOfChildNode[node.value] = count;

        return count;
    }
}
