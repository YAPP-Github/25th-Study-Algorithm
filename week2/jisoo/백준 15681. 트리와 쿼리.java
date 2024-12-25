import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ-15681: 트리와 쿼리
// U를 루트로 하는 서브트리에 속한 정점의 수 출력하기
public class BOJ_15681 {
    static StringTokenizer st;
    static ArrayList<ArrayList<Integer>> tree;
    static int[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); int R = Integer.parseInt(st.nextToken()); int Q = Integer.parseInt(st.nextToken());
        tree = new ArrayList<>();
        list = new int[N+1];
        for (int i=0; i<=N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            tree.get(U).add(V);
            tree.get(V).add(U);
        }

        dfs(R, -1);
        for (int i=0; i<Q; i++) {
            int V = Integer.parseInt(br.readLine());
            System.out.println(list[V]);
        }

    }

    private static void dfs(int v, int parent) {
        list[v] = 1;
        // v 기준으로 자식 트리 쫙 확인
        for (int child: tree.get(v)) {
            if (child==parent) continue;
            dfs(child, v);
            list[v] += list[child];
        }
    }
}
