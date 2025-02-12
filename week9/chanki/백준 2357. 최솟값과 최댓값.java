import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2357 {

    private static int[] origin;
    // 최대, 최소별로 세그먼트 트리 생성
    private static int[] minSegmentTree;
    private static int[] maxSegmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineInputs = br.readLine().split(" ");
        int number = Integer.parseInt(firstLineInputs[0]);
        int numberOfRange = Integer.parseInt(firstLineInputs[1]);

        origin = new int[number + 1];
        for (int i = 1; i <= number; i++) {
            origin[i] = Integer.parseInt(br.readLine());
        }

        initializeSegmentTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfRange; i++) {
            String[] range = br.readLine().split(" ");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);

            int[] result = findSegmentTree(start, end, 1, number, 1);
            sb.append(result[1]).append(" ").append(result[0]).append("\n");
        }

        System.out.println(sb);
    }

    // 세그먼트 트리 초기화
    private static void initializeSegmentTree() {
        int segmentTreeSize = 1;
        while (Math.pow(segmentTreeSize, 2) <= origin.length) {
            segmentTreeSize++;
        }
        // 사이즈 넉넉하게 선언 (ArrayIndexOutOfBounds 발생해서..)
        segmentTreeSize = (int) Math.pow(segmentTreeSize, 2) * 4 + 1;

        minSegmentTree = new int[segmentTreeSize];
        maxSegmentTree = new int[segmentTreeSize];
        initializeSegmentTreeElement(1, origin.length - 1, 1);
    }

    // top-down 이라 정해진 범위에서의 최대 최소는 수작업으로 한 번씩 구해줌
    // bottom-up 이면 안 그래도 되긴 할텐데.. 방법이 마땅히
    private static void initializeSegmentTreeElement(int start, int end, int index) {
        if (start == end) {
            maxSegmentTree[index] = origin[start];
            minSegmentTree[index] = origin[start];
            return;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, origin[i]);
            min = Math.min(min, origin[i]);
        }
        maxSegmentTree[index] = max;
        minSegmentTree[index] = min;

        int mid = (start + end) / 2;
        initializeSegmentTreeElement(start, mid, index * 2);
        initializeSegmentTreeElement(mid + 1, end, index * 2 + 1);
    }

    // result[0] = max, result[1] = min
    // 정해진 범위를 좁혀 나가며, 좁혀진 범위가 추출하고자 하는 대상 범위 안에 들어갔다면 최대, 최소 반환
    // dfs로 탐색하므로, 범위가 벗어나지 않았다면 모든 트리를 탐색하므로 상관 없음
    // 예시) 1~10을 탐색하는 경우
    // 3과 4~5를 담고 있는 인덱스를 따로 조회하나, 그 결과를 타고 올라가다보면 동일함
    private static int[] findSegmentTree(int left, int right, int start, int end, int index) {
        if (left <= start && end <= right) {
            return new int[]{maxSegmentTree[index], minSegmentTree[index]};
        }
        if ((start == end) || (start > right || end < left)) {
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
        }

        int mid = (start + end) / 2;
        int[] leftResult = findSegmentTree(left, right, start, mid, index * 2);
        int[] rightResult = findSegmentTree(left, right, mid + 1, end, index * 2 + 1);
        return new int[]{Math.max(leftResult[0], rightResult[0]), Math.min(leftResult[1], rightResult[1])};
    }
}
