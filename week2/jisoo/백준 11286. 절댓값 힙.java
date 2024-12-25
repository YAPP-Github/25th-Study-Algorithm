import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// BOJ-11286: 절댓값 힙
// 1. 배열에 정수 x 넣음
// 2. 절대값 가장 작은 값 출력 후, 제거
/// 그 값이 여러개라면, 가장 작은 수 출력하고 제거
public class BOJ_11286 {
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int a = Math.abs(o1);
                int b = Math.abs(o2);
                if (a>b) {
                    return a-b;
                } else if (a==b) {
                    if (o1>o2) return 1;
                    else return -1;
                }
                else return -1;
            }
        });

        while (N-->0) {
            int x = Integer.parseInt(br.readLine());
            if (x!=0) {
                pq.add(x);
            }
            else {
                if (!pq.isEmpty()) {
                    System.out.println(pq.poll());
                }
                else System.out.println("0");
            }
        }
    }
}
