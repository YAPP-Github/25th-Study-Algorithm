import java.util.*;
import java.io.*;

public class Main {
    static boolean[][] map = new boolean[101][101];
    // (dx, dy) 배열은 각 방향에 따른 x, y좌표 변화량
    // 0: 오른쪽, 1: 위쪽, 2: 왼쪽, 3: 아래쪽
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 시작 점
            int y = Integer.parseInt(st.nextToken()); // 시작 점
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            int g = Integer.parseInt(st.nextToken()); // 세대
            
            drawDragon(x, y, d, g);
        }
        System.out.print(checkSquare());
    }
    
    // 드래곤 커브 그리기
    static void drawDragon(int x, int y, int d, int g) {
        List<Integer> dirList = new ArrayList<>(); // 방향 리스트
        dirList.add(d); // 0세대 방향 추가
        addDir(dirList, g); // 1~g세대 방향 추가
        makeMap(dirList, x, y); // map 그리기
    }
    
    // 1세대 ~ g세대까지 방향 추가하기
    static void addDir(List<Integer> dirList, int g){
        for (int i = 1; i <= g; i++) {
            // 현재 세대까지 만들어진 방향 리스트를 역순으로 순회하면서
            // 각 방향의 다음 방향 (현재 방향 + 1 % 4) 를 리스트에 추가
            for (int j = dirList.size() - 1; j >= 0; j--) {
                dirList.add((dirList.get(j) + 1) % 4);
            }
        }
    }
    
    // map에 드래곤 커브 좌표 찍기
    static void makeMap(List<Integer> dirList, int x, int y){
        map[y][x] = true;
        for (Integer dir : dirList) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = true;
        }
    }
    
    // 정사각형 개수 세기
    static int checkSquare(){
        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 해당 위치가 모두 드래곤 커브의 일부라면 정사각형 개수를 증가
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
