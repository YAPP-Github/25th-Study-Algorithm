import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class B13549 {
    private static class Node {
        int point;
        int time;

        public Node(int point, int time) {
            this.point = point;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLineInput = br.readLine().split(" ");
        int start = Integer.parseInt(firstLineInput[0]);
        int end = Integer.parseInt(firstLineInput[1]);

        // 목적지가 더 작은 경우, 무조건 -1로만 이동할 수 밖에 없음
        if (start >= end) {
            System.out.println(start - end);
            return;
        }

        // 최소 방문 비용 저장
        int[] minimumTimes = new int[100001];
        Arrays.fill(minimumTimes, Integer.MAX_VALUE);

        // 시간 오름차순으로 다음 방문지를 뽑기 위한 priority queue
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
        pq.add(new Node(start, 0));
        minimumTimes[start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            // 방문 비용이 기존보다 크면 처리할 이유가 없음
            if (minimumTimes[node.point] < node.time) {
                continue;
            }

            // -1 이동, 좌표 검사 및 최소비용검사
            if (node.point - 1 >= 0 && minimumTimes[node.point - 1] > node.time + 1) {
                minimumTimes[node.point - 1] = node.time + 1;
                pq.add(new Node(node.point - 1, node.time + 1));
            }
            // +1 이동, 좌표 검사 및 최소비용검사
            if (node.point + 1 <= 100000 && minimumTimes[node.point + 1] > node.time + 1) {
                minimumTimes[node.point + 1] = node.time + 1;
                pq.add(new Node(node.point + 1, node.time + 1));
            }
            // *2 이동, 좌표 검사 및 최소비용검사
            if (node.point * 2 <= 100000 && minimumTimes[node.point * 2] > node.time) {
                minimumTimes[node.point * 2] = node.time;
                pq.add(new Node(node.point * 2, node.time));
            }
        }

        System.out.println(minimumTimes[end]);
    }
}
