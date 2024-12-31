import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Result> q = new PriorityQueue<Result>((o1, o2) -> {
            if(o1.score != o2.score){
                return o2.score - o1.score; // 점수 높은 순
            }
            return o1.penalty - o2.penalty; // 점수가 같으면 패널티 낮은 순
        });
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        // 큐에 참가자 결과 넣기
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int score = Integer.parseInt(st.nextToken());
            int penalty = Integer.parseInt(st.nextToken());
            q.offer(new Result(score, penalty));
        }
        
        // 5등의 점수 찾기
        int fifthScore = 0;
        int answer = 0;
        for(int i = 0; i < 5; i++) {
            Result r = q.poll();
            if(i == 4) {
                fifthScore = r.score;
            }
        }

        // 남은 학생들 중 5등과 같은 점수인 학생 세기
        while(!q.isEmpty() && q.peek().score == fifthScore) {
            q.poll();
            answer++;
        }
        System.out.print(answer);
    }
    
    // 해결한 문제 수 + 패널티 총합 클래스
    static class Result {
        int score; // 해결한 문제 수
        int penalty; // 패널티 총합
        
        public Result (int score, int penalty){
            this.score = score;
            this.penalty = penalty;
        }
    }
}
