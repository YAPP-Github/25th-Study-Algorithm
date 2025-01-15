
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PR16236 {

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        int[] cur = null;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int location = Integer.parseInt(st.nextToken());
                map[i][j] = location;

                if (location == 9) {
                    cur = new int[]{i, j};
                    map[i][j] = 0;
                }
            }
        }

        int size = 2;
        int eat = 0; // 먹은 물고기 수
        int move = 0; // 움직인 총 거리

        while (true) {
            PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) ->
                o1[2] != o2[2] ? Integer.compare(o1[2], o2[2])
                    : (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0])
                        : Integer.compare(o1[1], o2[1]))
            );
            boolean[][] visit = new boolean[N][N];

            que.add(new int[]{cur[0], cur[1], 0});
            visit[cur[0]][cur[1]] = true;

            boolean ck = false; // 상어가 먹이를 먹었는지 체크할 변수

            while (!que.isEmpty()) {
                cur = que.poll();

                if (map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < size) {
                    map[cur[0]][cur[1]] = 0; // 물고기를 제거
                    eat++;
                    move += cur[2]; // 움직인 거리를 총 움직인 거리에 추가
                    ck = true; // 먹이 먹었다고 체크
                    break;
                }

                for (int k = 0; k < 4; k++) {
                    int ny = cur[0] + dy[k];
                    int nx = cur[1] + dx[k];

                    if (ny < 0 || nx < 0 || nx >= N || ny >= N || visit[ny][nx]
                        || map[ny][nx] > size) {
                        continue;
                    }

                    que.add(new int[]{ny, nx, cur[2] + 1});
                    visit[ny][nx] = true;
                }
            }

            if (!ck) // 큐가 비워질 때까지 먹이를 먹은적이 없다면, 더 이상 먹은 물고기가 없으므로 탈출
            {
                break;
            }

            if (size == eat) { // 사이즈와 먹이를 먹은 수가 동일하다면 상어의 크기를 증가
                size++;
                eat = 0;
            }
        }

        System.out.println(move);

    }

}
