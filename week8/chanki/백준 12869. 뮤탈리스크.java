import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class B12869 {
    static class Combination {
        int first;
        int second;
        int third;

        public Combination(int first, int second, int third) {
            this.first = Math.max(first, 0);
            this.second = Math.max(second, 0);
            this.third = Math.max(third, 0);
        }

        public boolean isDone() {
            return this.first <= 0 && this.second <= 0 && this.third <= 0;
        }

        // Set에서 contains 판단을 위해 equals와 hashcode를 override
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Combination other)) return false;

            // 세 개의 값을 정렬하여 리스트로 비교
            List<Integer> thisValues = Arrays.asList(this.first, this.second, this.third);
            List<Integer> otherValues = Arrays.asList(other.first, other.second, other.third);
            Collections.sort(thisValues);
            Collections.sort(otherValues);

            return thisValues.equals(otherValues);
        }

        @Override
        public int hashCode() {
            List<Integer> values = Arrays.asList(first, second, third);
            Collections.sort(values);
            return Objects.hash(values.get(0), values.get(1), values.get(2));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfWorker = Integer.parseInt(br.readLine());
        // 한 개인 경우에는 9로 나눈 결과값을 올림하여 반환
        if (numberOfWorker == 1) {
            System.out.println((int) Math.ceil(Double.parseDouble(br.readLine()) / 9));
            return;
        }

        List<Integer> health = Arrays.stream(br.readLine().split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        while (health.size() < 3) {
            health.add(0);
        }

        int[][] attackWays = new int[][]{
            {9, 3, 1},
            {9, 1, 3},
            {3, 9, 1},
            {3, 1, 9},
            {1, 3, 9},
            {1, 9, 3}
        };

        int result = 0;
        // BFS를 진행
        Deque<Combination> deque = new ArrayDeque<>();
        // 이미 처리한 조합은 제외
        Set<Combination> alreadyProcessedCombinations = new HashSet<>();
        Combination firstCombination = new Combination(health.get(0), health.get(1), health.get(2));
        deque.add(firstCombination);
        alreadyProcessedCombinations.add(firstCombination);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Combination combination = deque.poll();
                // 만약 추출한 조합이 0, 0, 0이면 종료
                if (combination.isDone()) {
                    deque.clear();
                    break;
                }
                for (int j = 0; j < 6; j++) {
                    Combination next = new Combination(
                        combination.first - attackWays[j][0],
                        combination.second - attackWays[j][1],
                        combination.third - attackWays[j][2]
                    );
                    if (alreadyProcessedCombinations.contains(next)) {
                        continue;
                    }
                    deque.add(next);
                    alreadyProcessedCombinations.add(next);
                }
            }
            result++;
        }
        // while문 안에서 result++이 한 번 더 처리되기 때문에, 출력 시에는 result - 1
        System.out.println(result - 1);
    }
}
