import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class PR18513 {

    private static int[] dx = {-1, 1};
    private static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] house = new int[N];
        int i = 0;
        while(st.hasMoreTokens()) {
            house[i++] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs(house, K));
    }

    private static long bfs(int[] house, int K) {
        long result = 0;
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        for(int i = 0; i < house.length; i++) {
            queue.add(new int[]{house[i], 0});
            visited.add(house[i]);
        }

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            //선형에서 위치 갱신
            for(int i = 0; i < 2; i++) {
                int x = cur[0] + dx[i];

                if(!visited.contains(x)) {
                    queue.add(new int[]{x, cur[1] + 1});
                    visited.add(x);

                    result += (cur[1] + 1);
                    count++;

                    if(count == K) {
                        return result;
                    }
                }
            }
        }

        return result;
    }

}
