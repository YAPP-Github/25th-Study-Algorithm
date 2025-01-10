import java.util.*;
import java.io.*;

class Main {
    static List<ArrayList<Integer>> map = new ArrayList<>();
    static int[] visited; // 각 지점까지의 최단 거리를 저장할 배열
    static Queue<Integer> q = new LinkedList<>(); // BFS 구현을 위한 큐
    static int n, m, s, e;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        m =Integer.valueOf(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        s = Integer.valueOf(st.nextToken());
        e = Integer.valueOf(st.nextToken());
        
        // 인접 리스트 초기화 (0번 인덱스부터 n번 인덱스까지)
        for(int i = 0; i <= n; i++){
            map.add(new ArrayList<>());
        }
        
        // 연결된 지점 정보 저장
        // 양방향 그래프이므로 양쪽 모두에 추가
        for(int i = 0; i < m; i++){
            st= new StringTokenizer(br.readLine());
            int x = Integer.valueOf(st.nextToken());
            int y = Integer.valueOf(st.nextToken());
            map.get(x).add(y);
            map.get(y).add(x);
        }
 
        // visited 배열을 최댓값으로 초기화
        visited = new int[n+1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        
        bfs(s, e);
        System.out.print(visited[e]);
    }
    
    static void bfs(int s, int e) {
        // 시작점의 거리를 0으로 설정
        visited[s] = 0;
        q.offer(s);
 
        while(!q.isEmpty()){
            int now = q.poll();
            
            // 도착점에 도달했다면 탐색 종료
            if(now == e) {
                break;
            }
 
            // 텔레포트 이동
            for(int next : map.get(now)){
                // 다음 지점까지의 거리가 현재까지의 거리+1보다 크면 갱신
                if(visited[next] > visited[now] + 1){
                    visited[next] = visited[now] + 1;
                    q.offer(next);
                }
            }
 
            // 오른쪽으로 한 칸 걷기
            if(now + 1 <=n && visited[now+1] > visited[now] + 1){
                visited[now+1] = visited[now] + 1;
                q.offer(now+1);
            }
            
            // 왼쪽으로 한 칸 걷기
            if(now - 1 >= 1 && visited[now-1] > visited[now] + 1){
                visited[now-1] = visited[now] + 1;
                q.offer(now-1);
            }
        }
    }
}
