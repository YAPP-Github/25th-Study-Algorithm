import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj-13549: 숨바꼭질 3
// 현재 점 N에 있고 동생은 K에 있음
// 수빈이는 걸으면 1초후에 -1,+1 가능하고, 순간이동하면 0초 후에 2*x로 이동 가능
// 동생 찾을 수 있는 가장 빠른 시간은 몇 초 후?
public class BOJ_13549 {
    static int N,K;
    static boolean[] visited;
    static int answer = 100000;
    static int max = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
        visited = new boolean[max+1];
        bfs();

        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(N, 0));

        while (!q.isEmpty()) {
            Node n = q.poll();
            visited[n.x] = true;
            if (n.x==K) answer = Math.min(answer, n.time);

            if (n.x*2 <= max && !visited[n.x * 2]) {
                q.add(new Node(n.x*2, n.time));
            }
            if (n.x+1 <= max && !visited[n.x+1]) {
                q.add(new Node(n.x+1, n.time+1));
            }
            if (n.x-1 >= 0 && !visited[n.x-1]) {
                q.add(new Node(n.x-1, n.time+1));
            }
        }
    }

    public static class Node {
        int x;
        int time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}
