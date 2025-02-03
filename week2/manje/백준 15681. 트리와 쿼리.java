import java.util.*;
import java.io.*;
public class Main {
    // list: 양방향 연결 관계를 저장하는 인접 리스트
    // tree: 단방향(부모->자식) 관계만 저장하는 인접 리스트
    static ArrayList<Integer>[] list, tree;
    // parent: 각 노드의 부모 노드 번호 저장
    // subTreeSize: 각 노드를 루트로 하는 서브트리의 노드 개수 저장
    static int parent[], subTreeSize[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());    // 노드의 수
        int r = Integer.parseInt(st.nextToken());    // 루트 노드 번호
        int q = Integer.parseInt(st.nextToken());    // 쿼리의 수
        
        // 배열 초기화
        parent = new int[n+1];
        subTreeSize = new int[n+1];
        list = new ArrayList[n+1];
        tree = new ArrayList[n+1];
        
        // 각 노드의 인접 리스트 초기화
        for(int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }
        
        // 간선 정보 입력 받기 (양방향)
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 양방향으로 연결 관계 저장
            list[u].add(v);
            list[v].add(u);
        }
        
        // 루트부터 시작하여 트리 구조 생성
        makeTree(r, -1);
        // 각 노드의 서브트리 크기 계산
        countSubtreeNodes(r);
        
        // 쿼리 처리
        StringBuffer sb = new StringBuffer();
        while(q-- > 0) {
            int query = Integer.parseInt(br.readLine());
          
            // 해당 노드를 루트로 하는 서브트리의 크기 출력
            sb.append(subTreeSize[query]).append("\n");
        }
        
        System.out.print(sb);
    }
    
    // 양방향 그래프를 단방향 트리로 변환
    public static void makeTree(int curNode, int p) {
        // 현재 노드와 연결된 모든 노드 확인
        for(int node : list[curNode]) {
          
            // 부모 노드가 아닌 경우에만 자식으로 추가
            if(node != p) {
                tree[curNode].add(node);    // 트리에 자식 노드 추가
                parent[node] = curNode;      // 부모 정보 저장
                makeTree(node, curNode);     // 재귀적으로 자식 노드 처리
            }
        }
    }
    
    // 각 노드의 서브트리 크기 계산
    public static void countSubtreeNodes(int curNode) {
        subTreeSize[curNode] = 1;    // 현재 노드도 포함하므로 1로 초기화
      
        // 모든 자식 노드들의 서브트리 크기를 더함
        for(int node : tree[curNode]) {
            countSubtreeNodes(node);    // 재귀적으로 자식 노드의 서브트리 크기 계산
            subTreeSize[curNode] += subTreeSize[node];    // 현재 노드의 서브트리 크기에 더함
        }
    }
}
