import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
      
        // 양방향 순환 큐 생성
        LinkedList<Integer> q = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            q.add(i);
        }
      
        // 뽑아내려고 하는 위치 배열 생성
        int[] idx = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            idx[i] = Integer.parseInt(st.nextToken());
        }

        // 위치 별 연산 횟수 측정하기
        int answer = 0;
        for(int i = 0; i < m; i++){
            int targetIdx = q.indexOf(idx[i]); // 목표 위치
            int halfIdx; // 중간 위치
            if(q.size() % 2 == 0) { // 큐 크기가 짝수면 1을 빼고 중간 위치 저장
                halfIdx = q.size() / 2 - 1;
            } else { // 큐 크기가 홀수면 바로 중간 위치 저장
                halfIdx = q.size() / 2;
            }
            if(targetIdx <= halfIdx) { // 목표 위치가 큐 중간 위치보다 작거나 같으면
                // 문제의 2번 연산 수행하고 정답 +1
                for(int j = 0; j < targetIdx; j++) {
                    int temp = q.removeFirst();
                    q.addLast(temp);
                    answer++;
                }
            } else { // 목표 위치가 큐 중간 위치보다 크면
                // 문제의 3번 연산 수행하고 정답 +1
                for(int j = 0; j < q.size() - targetIdx; j++) {
                    int temp = q.removeLast();
                    q.addFirst(temp);
                    answer++;
                }
            }
            q.removeFirst(); // 위에서 2 or 3번 연산을 통해 목표 위치를 맨 앞으로 위치시키고 1번 연산 수행
        }
        System.out.print(answer);
    }
}
