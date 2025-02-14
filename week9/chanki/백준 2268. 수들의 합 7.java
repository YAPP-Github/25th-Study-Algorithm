import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2268 {

    private static long[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLineInput = br.readLine().split(" ");
        int n = Integer.parseInt(firstLineInput[0]);
        int numberOfCommands = Integer.parseInt(firstLineInput[1]);

        // 원본
        int[] originArr = new int[n + 1];
        // 세그먼트 트리
        segmentTree = new long[n * 4];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCommands; i++) {
            String[] command = br.readLine().split(" ");
            int commandType = Integer.parseInt(command[0]);
            int number1 = Integer.parseInt(command[1]);
            int number2 = Integer.parseInt(command[2]);
            if (commandType == 0) {
                sb.append(
                    findSumInRange(Math.min(number1, number2), Math.max(number1, number2), 1, n, 1)
                ).append("\n");
            } else {
                int valueGap = originArr[number1] - number2;
                modifySegmentTree(number1, valueGap, 1, n, 1);
                // 세그먼트 트리 수정 후에는 원본도 변경해줘야 한다.
                // 변경한 특정 index를 다시 또 수정하면 valueGap이 달라질 수 있기 때문이다.
                originArr[number1] = number2;
            }
        }

        System.out.println(sb);
    }

    private static long findSumInRange(int rangeStart, int rangeEnd, int left, int right, int index) {
        // 범위 안에 찰떡으로 들어가 있으면 바로 반환
        if (rangeStart <= left && right <= rangeEnd) {
            return segmentTree[index];
        }

        // 세그먼트 트리를 계속 분할해 내려가도 나오지 않을 범위라면 종료
        // 누적합 세그먼트 트리이므로 0 반환
        if (right < rangeStart || rangeEnd < left) {
            return 0;
        }

        // 좌우로 나누어 진행한 후 합산하여 반환
        int mid = left + (right - left) / 2;
        return findSumInRange(rangeStart, rangeEnd, left, mid, index * 2) +
            findSumInRange(rangeStart, rangeEnd, mid + 1, right, index * 2 + 1);
    }

    // valueGap - 각 노드는 범위의 합계를 구해 놓은 것이므로, valueGap 만큼만 수정해주면 됨
    private static void modifySegmentTree(int targetIndex, long valueGap, int left, int right, int index) {
        // 수정 범위에 들어오지 않는 노드면 바로 종료
        if (targetIndex < left || right < targetIndex) {
            return;
        }
        // 값 수정
        segmentTree[index] -= valueGap;
        // 더 이상 분할이 불가능하면 종료
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        modifySegmentTree(targetIndex, valueGap, left, mid, index * 2);
        modifySegmentTree(targetIndex, valueGap, mid + 1, right, index * 2 + 1);
    }
}
