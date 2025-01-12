import java.util.*;

class Solution {
    static ArrayList<Integer>[] graph;
    static int maxSheep = 0;
    
    public int solution(int[] info, int[][] edges) {
        // 그래프 초기화
        graph = new ArrayList[info.length];
        for(int i = 0; i < info.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 그래프 연결 관계 설정
        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
        
        // 처음 방문 가능한 노드는 루트 노드의 자식 노드들
        ArrayList<Integer> nextNodes = new ArrayList<>();
        nextNodes.addAll(graph[0]);
        
        // DFS 시작 (루트 노드부터)
        // 파라미터: 현재 노드, 현재까지 모은 양의 수, 늑대의 수, 다음에 방문 가능한 노드들, 노드 정보
        dfs(0, 1, 0, nextNodes, info);
        
        return maxSheep;
    }
    
    void dfs(int current, int sheep, int wolf, ArrayList<Integer> nextNodes, int[] info) {
        // 현재 양의 수가 최댓값보다 크면 갱신
        maxSheep = Math.max(maxSheep, sheep);
        
        // 다음 방문 가능한 모든 노드에 대해 탐색
        for(int i = 0; i < nextNodes.size(); i++) {
            int next = nextNodes.get(i);
            
            // 다음 방문 가능한 노드들의 목록 생성
            ArrayList<Integer> newNextNodes = new ArrayList<>(nextNodes);
            newNextNodes.remove(i); // 현재 방문할 노드는 제거
            
            // 다음 방문할 노드의 자식 노드들 추가
            newNextNodes.addAll(graph[next]);
            
            // 다음 노드가 양인 경우
            if(info[next] == 0) {
                dfs(next, sheep + 1, wolf, newNextNodes, info);
            } else if(sheep > wolf + 1) {  // 다음 노드가 늑대인 경우, 늑대를 추가해도 양이 잡아먹히지 않는 경우에만 진행
                dfs(next, sheep, wolf + 1, newNextNodes, info);
            }
        }
    }
}
