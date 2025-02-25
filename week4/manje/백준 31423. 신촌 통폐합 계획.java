import java.util.*;
import java.io.*;

public class Main {
    static int N;           // 대학의 수
    static String[] univs;  // 대학 이름을 저장하는 배열
    static int[] next;      // 링크드 리스트의 다음 노드를 가리키는 배열
    static int[] tail;      // 각 리스트의 마지막 노드를 가리키는 배열
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 대학의 수 입력받기
        N = Integer.parseInt(br.readLine());
        
        // 배열 초기화
        univs = new String[N+1];
        next = new int[N+1];
        tail = new int[N+1];
        
        // 대학 이름 입력받고 링크드 리스트 초기화
        for(int i = 1; i <= N; i++){
            univs[i] = br.readLine();
            next[i] = i;    // 각 노드는 처음에 자기 자신을 가리킴
            tail[i] = i;    // 처음에는 각 노드가 자신의 테일
        }
        
        // 시작 대학 인덱스 (-1은 아직 결정되지 않았음을 의미)
        int cur = -1;
        
        // N-1개의 관계 입력받기
        for (int i = 1; i <= N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            
            // left 리스트의 끝에 right 리스트를 연결
            next[tail[left]] = right;
            
            // left 리스트의 테일을 right 리스트의 테일로 업데이트
            tail[left] = tail[right];
            
            // 시작 대학을 left로 설정 (마지막 입력의 left가 전체 리스트의 시작점)
            cur = left;
        }
        
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < N; i++) {
            sb.append(univs[cur]);
            cur = next[cur];
        }
        
        System.out.print(sb);
    }
}
