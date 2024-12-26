import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class PR15681 {

    // dp로 자식 노드들의 개수를 계산
    private static int[] dp;
    // map으로 트리 노드 초기화(무방향 그래프)
    private static Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            map.get(node1).add(node2);
            map.get(node2).add(node1);
        }

        countSubTreeNodes(R);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++) {
            int startNode = Integer.parseInt(br.readLine());

            sb.append(dp[startNode]).append("\n");
        }

        System.out.println(sb.toString());
    }

    // 재귀를 통해 루트 노드로부터 자식 노드들의 개수를 계산
    private static void countSubTreeNodes(int rootNode) {
        dp[rootNode] = 1;
        List<Integer> childs = map.get(rootNode);
        for (Integer child : childs) {
            if (dp[child] == 0) {
                countSubTreeNodes(child);
                dp[rootNode] += dp[child];
            }
        }
    }
}
