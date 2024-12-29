import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> arr = new PriorityQueue<Integer>((o1, o2) -> {
            // 절댓값이 다르면 절댓값 비교해 작은 순으로 담기
            if (Math.abs(o1) != Math.abs(o2)) {
                return Math.abs(o1) - Math.abs(o2);
            }
            // 절댓값이 같으면 실제 값 비교해 작은 순으로 담기
            return o1 - o2;
        });
        for(int i = 0; i < N; i++){
            int x = Integer.parseInt(br.readLine());
            if(x != 0){ // x가 0이 아닐 경우 배열에 담기
                arr.add(x);
            } else { // x가 0일 경우
                if(arr.isEmpty()) { // 빈 배열이면 0 출력
                    System.out.println(0);
                } else { // 배열에서 최솟값 출력
                    System.out.println(arr.poll());
                }
            }
        }
    }
}
