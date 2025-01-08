import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B31423 {

    static class B31423Node {
        String value;
        B31423Node next;
        B31423Node last;

        public B31423Node(String value) {
            this.value = value;
            this.last = this;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfUniv = Integer.parseInt(br.readLine());
        // 인덱스별 노드 정보를 초기화합니다
        Map<Integer, B31423Node> nodeMap = new HashMap<>();
        for (int i = 0; i < numberOfUniv; i++) {
            nodeMap.put(i, new B31423Node(br.readLine()));
        }

        int beforeIndex = 0, nextIndex;
        for (int i = 0; i < numberOfUniv - 1; i++) {
            String[] inputConnectionInfo = br.readLine().split(" ");
            beforeIndex = Integer.parseInt(inputConnectionInfo[0]) - 1;
            nextIndex = Integer.parseInt(inputConnectionInfo[1]) - 1;

            B31423Node beforeNode = nodeMap.get(beforeIndex);
            B31423Node nextNode = nodeMap.get(nextIndex);

            // last라는 별도의 변수를 통해 시간 복잡도를 줄입니다.
            // 그렇지 않으면 last를 탐색하기 위해 많은 연산이 필요합니다.
            // last를 선언하지 않는 경우의 시간 복잡도는 O(n^2) 입니다.
            beforeNode.last.next = nextNode;
            beforeNode.last = nextNode.last;
        }

        // 가장 마지막 입력이 A B라고 한다면, A가 head node 입니다.
        B31423Node head = nodeMap.get(beforeIndex);
        StringBuilder answer = new StringBuilder();
        B31423Node node = head;

        while (node != null) {
            answer.append(node.value);
            node = node.next;
        }

        System.out.println(answer);
    }
}
