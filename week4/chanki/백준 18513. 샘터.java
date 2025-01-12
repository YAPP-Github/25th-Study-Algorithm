import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class B18513 {

    private static class House {
        long index;
        int unhappiness;

        public House(long index, int unhappiness) {
            this.index = index;
            this.unhappiness = unhappiness;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineInput = br.readLine().split(" ");
        int numberOfHouse = Integer.parseInt(firstLineInput[1]);

        // 각 집이 설치될 위치를 불행도 순으로 정렬
        PriorityQueue<House> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.unhappiness));
        // 방문했던 곳을 기록
        Set<Long> filledIndex = new HashSet<>();

        Arrays.stream(br.readLine().split(" "))
            .forEach(it -> {
                long value = Long.parseLong(it);
                pq.add(new House(value, 0));
                filledIndex.add(value);
            });

        int filledHouseCount = 0;
        long sumOfUnhappiness = 0;
        // BFS
        // 샘물을 시작으로, 주변으로 한 칸 씩 이동
        fillHouse:
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int i = 0; i < size; i++) {
                House house = pq.poll();
                // 샘물은 계산에서 제외하기 위함
                if (house.unhappiness > 0) {
                    sumOfUnhappiness += house.unhappiness;
                    filledHouseCount++;
                }

                // 방금 뽑은 집에서 왼쪽으로 한 칸
                if (!filledIndex.contains(house.index + 1)) {
                    filledIndex.add(house.index + 1);
                    pq.add(new House(house.index + 1, house.unhappiness + 1));
                }

                // 방금 뽑은 집에서 오른쪽으로 한 칸
                if (!filledIndex.contains(house.index - 1)) {
                    filledIndex.add(house.index - 1);
                    pq.add(new House(house.index - 1, house.unhappiness + 1));
                }

                if (filledHouseCount >= numberOfHouse) {
                    break fillHouse;
                }
            }
        }

        System.out.println(sumOfUnhappiness);
    }
}
