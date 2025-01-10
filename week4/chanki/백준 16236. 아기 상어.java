import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class B16236 {

    // 북서동남 -> 위, 왼쪽 우선
    static int[] dRow = {-1, 0, 0, 1};
    static int[] dColumn = {0, -1, 1, 0};

    static class Block {
        int row;
        int column;
        int value;
        int distanceFromShark;

        public Block(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
            this.distanceFromShark = 0;
        }
    }

    static class Shark {
        Block block;
        int size;
        int fishCount;
        int moveTime;

        public Shark(Block block) {
            this.block = block;
            this.size = 2;
            this.fishCount = 0;
            this.moveTime = 0;
        }

        void moveTo(Block block, int time) {
            moveTime += time;
            // 먹은 물고기 수와 사이즈 갱신
            if (0 < block.value && block.value <= this.size) {
                this.fishCount++;
                while (this.size <= this.fishCount) {
                    this.fishCount -= this.size;
                    this.size++;
                }
            }

            // 내가 있던 블럭은 물고기가 없어졌으므로 0
            // 새로운 블럭으로 대체, 상어가 있음을 표시
            this.block.value = 0;
            this.block = block;
            block.value = 9;
        }
    }

    static Block[][] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        blocks = new Block[size][size];
        Map<Integer, Integer> countBySize = new HashMap<>();

        int sharkRow = 0, sharkColumn = 0;
        // block map 초기화
        for (int r = 0; r < size; r++) {
            String[] input = br.readLine().split(" ");
            for (int c = 0; c < size; c++) {
                int number = Integer.parseInt(input[c]);
                blocks[r][c] = new Block(r, c, number);
                if (1 <= number && number <= 6) {
                    countBySize.put(number, countBySize.getOrDefault(number, 0) + 1);
                } else if (number == 9) {
                    sharkRow = r;
                    sharkColumn = c;
                }
            }
        }

        Shark shark = new Shark(blocks[sharkRow][sharkColumn]);
        // 거리, 위, 왼쪽 순으로 우선순위 부여
        PriorityQueue<Block> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.distanceFromShark == o2.distanceFromShark) {
                if (o1.row == o2.row) {
                    return o1.column - o2.column;
                }
                return o1.row - o2.row;
            }
            return o1.distanceFromShark - o2.distanceFromShark;
        });

        // 먹을 수 있는 물고기가 있는지 판단
        while (shark.size >= getMinimumSizeOfFish(countBySize)) {
            pq.add(shark.block);
            // block map에 있는 Block 객체로만 비교하므로, 참조값을 통해 비교 가능
            Set<Block> visitedBlocks = new HashSet<>();
            visitedBlocks.add(shark.block);

            // 경로 탐색
            int time = 0;
            boolean anyOfBlockCanEatFish = false;
            while (!pq.isEmpty() && !anyOfBlockCanEatFish) {
                int sizeOfPq = pq.size();
                // 한 칸 더 이동할 때마다 time 증가
                time++;
                for (int movementCount = 0; movementCount < sizeOfPq; movementCount++) {
                    Block now = pq.poll();
                    // 북서동남 방문
                    for (int i = 0; i < 4; i++) {
                        int row = now.row + dRow[i];
                        int column = now.column + dColumn[i];
                        // 타당한 좌표인지 검증 (범위를 벗어나진 않았는지, 이동할 수 있는지)
                        if (!isValidBlockForVisit(row, column, size, shark)) {
                            continue;
                        }
                        Block block = blocks[row][column];
                        // 방문했는지 확인
                        if (!visitedBlocks.contains(block)) {
                            block.distanceFromShark = time;
                            pq.add(block);
                            visitedBlocks.add(block);
                            // 먹을 수 있는 칸인지 판단, 먹을 수 있는 물고기를 찾았다는 플래그
                            if (canEatFish(block, shark) && !anyOfBlockCanEatFish) {
                                anyOfBlockCanEatFish = true;
                            }
                        }
                    }
                }
            }

            if (pq.isEmpty()) {
                break;
            }

            Block block = pq.poll();
            while (!pq.isEmpty() && !canEatFish(block, shark)) {
                block = pq.poll();
            }
            // 더 먹을 물고기가 남아있는지 판단을 위한 Map 업데이트
            countBySize.put(block.value, countBySize.get(block.value) - 1);
            // 움직임에 관련한 처리
            shark.moveTo(block, time);
            pq.clear();
        }

        System.out.println(shark.moveTime);
    }

    private static int getMinimumSizeOfFish(Map<Integer, Integer> countBySize) {
        return countBySize.keySet().stream()
            .filter(it -> countBySize.getOrDefault(it, 0) > 0)
            .min(Integer::compareTo)
            .orElse(Integer.MAX_VALUE);
    }

    // 범위 내에 있는지 & 물고기랑 크기가 같거나 더 큰지
    private static boolean isValidBlockForVisit(int row, int column, int size, Shark shark) {
        return (0 <= row && row <= size - 1 && 0 <= column && column <= size - 1)
            && (blocks[row][column].value <= shark.size);
    }

    private static boolean canEatFish(Block block, Shark shark) {
        return 0 < block.value && block.value < shark.size;
    }
}
