import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ-2630: 색종이 만들기
// 중간 부분을 잘라, 네 개의 N/2 x N/2 색종이로 나눔
// 나눈 색종이가 모두 같은 색이면 그대로, 아니면 4개로 나눔
// 잘라진 하얀색 종이(0)와 파란색 종이(1)의 개수는?
public class BOJ_2630 {
    static int white = 0, blue = 0;
    static int[][] paper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    private static void divide(int x, int y, int size) {
        if (isSameColor(x, y, size)) {
            if (paper[x][y] == 0) white++;
            else blue++;
            return;
        }

        int newSize = size / 2;
        divide(x, y, newSize);
        divide(x, y + newSize, newSize);
        divide(x + newSize, y, newSize);
        divide(x + newSize, y + newSize, newSize);
    }

    private static boolean isSameColor(int x, int y, int size) {
        int color = paper[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (color != paper[i][j]) return false;
            }
        }
        return true;
    }
}
