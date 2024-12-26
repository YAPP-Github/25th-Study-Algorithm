import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// BOJ-1021: 회전하는 큐
// 1. 첫 번째 원소 뽑음
// 2. 왼쪽으로 한 칸 이동
// 3. 오른쪽응로 한 칸 이동
// 주어진 순서대로 뽑는데 드는 2,3번 연산의 최솟값은?
public class BOJ_1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());

        LinkedList<Integer> q = new LinkedList<>();
        for (int i=1; i<=N; i++) {
            q.offer(i);
        }
        int[] nums = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i=0; i<M; i++) {
            int target = q.indexOf(nums[i]);
            int pivot;
            if (q.size()%2==0) {
                pivot = q.size()/2-1;
            } else {
                pivot = q.size()/2;
            }

            // 타깃이 앞에 있다면 2번
            if (target <= pivot) {
                for (int j=0; j<target; j++) {
                    int temp = q.pollFirst();
                    q.offerLast(temp);
                    answer++;
                }
            }
            else {
                for (int j=0; j<q.size()-target; j++) {
                    int temp = q.pollLast();
                    q.offerFirst(temp);
                    answer++;
                }
            }
            // 1번
            q.pollFirst();
        }

        System.out.println(answer);
    }
}
