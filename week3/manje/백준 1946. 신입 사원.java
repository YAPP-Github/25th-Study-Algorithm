import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 테스트 케이스 수
        int t = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < t; i++){
            // 지원자 수
            int n = Integer.parseInt(br.readLine());
            
            // 선발할 수 있는 인원 수 초기화
            int count = 1;
            
            // 지원자 배열 (인덱스는 서류 순위, 값은 면접 순위)
            int[] arr = new int[n+1];
            
            // 각 지원자의 서류 순위와 면접 순위를 입력받아 배열에 저장
            for(int j = 0; j < n; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                
                // 서류 순위 a, 면접 순위 b
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                // 서류 순위를 인덱스로 하여 면접 순위를 저장
                arr[a] = b;
            }
            
            // 서류 1등의 면접 순위를 기준으로 설정
            // 서류 1등은 무조건 선발
            int k = arr[1];
            
            // 서류 2등부터 n등까지 확인
            for(int j = 2; j <= n; j++){
                
                // 현재 지원자의 면접 순위가 이전까지의 최소 면접 순위보다 작으면
                if(arr[j] < k){
                    
                    // 최소 면접 순위를 갱신
                    k = arr[j];
                    
                    // 선발 인원 증가
                    count++;
                }
            }
            
            // 해당 테스트 케이스에서 선발할 수 있는 최대 인원 수 출력
            System.out.println(count);
        }
    }
}
