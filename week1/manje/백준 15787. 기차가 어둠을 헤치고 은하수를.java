import java.util.*;
import java.io.*;
public class Main {
   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());     
     
       // N: 기차의 수, M: 명령의 수
       int N = Integer.parseInt(st.nextToken());
       int M = Integer.parseInt(st.nextToken());
       
       // i번 기차의 좌석 상태를 비트로 표현 (0: 빈좌석, 1: 승객있음)
       int[] trains = new int[N + 1];
       
       // M개의 명령 처리
       for(int j = 0; j < M; j++){
           st = new StringTokenizer(br.readLine());
           int order = Integer.parseInt(st.nextToken());  // 명령 종류
           int i = Integer.parseInt(st.nextToken());      // 기차 번호
           // order가 1,2일 때만 x값 입력 (좌석 번호)
           int x = order <= 2 ? Integer.parseInt(st.nextToken()) : 0;
           
           switch (order) {
               case 1:  // x번 좌석에 승객 태우기
                   trains[i] |= (1 << x);
                   break;
               case 2:  // x번 좌석의 승객 하차
                   trains[i] &= ~(1 << x);
                   break;
               case 3:  // 모든 승객 한 칸씩 뒤로
                   trains[i] <<= 1;
                   trains[i] &= ((1 << 21) - 1);  // 20번 좌석 넘어가는 승객 하차
                   break;
               case 4:  // 모든 승객 한 칸씩 앞으로
                   trains[i] >>= 1;
                   trains[i] &= ~1;  // 맨 앞자리 승객 하차
                   break;
           }
       }
       
       // 1번부터 N번 기차까지 중 서로 다른 승객 배치의 수 계산
       int result = (int) Arrays.stream(trains, 1, N + 1)
               .distinct()
               .count();
       System.out.print(result);
   }
}
