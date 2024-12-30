import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class B1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineSplit = br.readLine().split(" ");
        Deque<Integer> deque = new ArrayDeque<>();
        int totalNumber = Integer.parseInt(firstLineSplit[0]);
        for (int i = 1; i <= totalNumber; i++) {
            deque.add(i);
        }

        Queue<Integer> extractTargets = new LinkedList<>();
        String[] extractTargetSplit = br.readLine().split(" ");
        for (String target : extractTargetSplit) {
            extractTargets.add(Integer.parseInt(target));
        }

        int result = 0;
        while (!extractTargets.isEmpty()) {
            // 원본의 첫번째가 타겟과 동일한 경우 원소 제거
            if (deque.peek().equals(extractTargets.peek())) {
                deque.removeFirst();
                extractTargets.remove();
                continue;
            }

            // 왼쪽 회전용 큐
            Deque<Integer> rotateLeftDeque = new ArrayDeque<>(deque);
            // 오른쪽 회전용 큐
            Deque<Integer> rotateRightDeque = new ArrayDeque<>(deque);

            while (true) {
                // 왼쪽 회전
                rotateLeftDeque.addLast(rotateLeftDeque.removeFirst());
                // 오른쪽 회전
                rotateRightDeque.addFirst(rotateRightDeque.removeLast());
                result++;

                // 완쪽 회전으로 발견하면 회전 종료 후 원본에 덮어쓰기
                if (rotateLeftDeque.peek().equals(extractTargets.peek())) {
                    deque = rotateLeftDeque;
                    break;
                }

                // 오른쪽 회전으로 발견하면 회전 종료 후 원본에 덮어쓰기
                if (rotateRightDeque.peek().equals(extractTargets.peek())) {
                    deque = rotateRightDeque;
                    break;
                }
            }

            // 원본과 타겟에서 첫번째 원소 제거
            deque.removeFirst();
            extractTargets.remove();
        }

        System.out.println(result);
    }
}
