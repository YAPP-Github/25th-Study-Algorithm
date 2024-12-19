import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class PR15685 {

    static private int n;
    static private final boolean[][] map = new boolean[101][101];
    static private List<Integer> dirList;
    static private int[] dx = {1, 0, -1, 0};
    static private int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dirList = new LinkedList<>();
            addDirAll(d, g);
            drawDrangon(x, y);
        }

        int ans = checkSquare();
        System.out.println(ans);
    }

    // 방향을 저장함
    private static void addDirAll(int d, int g) {
        dirList.add(d);

        // 1세대 ~ 3세대까지
        for(int i = 1; i <= g; i++) {
            // 방향의 규칙을 살려 저장함
            for(int j = dirList.size() - 1; j >= 0; j--) {
                dirList.add((dirList.get(j) + 1) % 4);
            }
        }
    }

    // map에 드래곤 커브를 그림
    private static void drawDrangon(int x, int y) {
        map[x][y] = true;

        int nx = x;
        int ny = y;

        for (int i = 0; i < dirList.size(); i++) {
            int dir = dirList.get(i);

            // x, y는 방향을 바꿔서 저장함
            nx += dx[dir];
            ny += dy[dir];

            map[nx][ny] = true;
        }
    }

    // 드래곤 커브 정사각형 개수를 게산
    private static int checkSquare() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
                    count++;
                }
            }
        }

        return count;
    }

}
