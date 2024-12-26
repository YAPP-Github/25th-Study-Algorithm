import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class PR1021 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        int totalMoveCount = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int idx = queue.indexOf(Integer.parseInt(st.nextToken()));

            int left = idx;
            int right = queue.size() - idx;

            if (left <= right) {
                totalMoveCount += left;

                while (left-- > 0) {
                    queue.addLast(queue.pollFirst());
                }
            } else {
                totalMoveCount += right;

                while (right-- > 0) {
                    queue.addFirst(queue.pollLast());
                }
            }

            queue.pollFirst();
        }

        System.out.println(totalMoveCount);
    }

}
