import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1074 {

    static int result = 0;
    static int destinationRow, destinationColumn;
    static int[] multipliersForRow = {0, 0, 1, 1};
    static int[] multipliersForColumn = {0, 1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineInput = br.readLine().split(" ");
        int size = (int) Math.pow(2, Double.parseDouble(firstLineInput[0]));
        destinationRow = Integer.parseInt(firstLineInput[1]);
        destinationColumn = Integer.parseInt(firstLineInput[2]);

        search(size, 0, 0);
        System.out.println(result);
    }

    private static void search(int size, int startRow, int startColumn) {
        int halfSize = size / 2;
        // Z 모양으로 탐색
        for (int i = 0; i < 4; i++) {
            int row = startRow + halfSize * multipliersForRow[i];
            int column = startColumn + halfSize * multipliersForColumn[i];

            // 만약 목적지가 블럭 안에 있으면, 내부 분할을 통해 추가 탐색 진행
            if (hasDestinationInBlock(halfSize, row, column)) {
                search(halfSize, row, column);
                break;
            }
            // 만약 범위 밖이라면 탐색 들어갈 필요 없이, 정사각형 크기만큼 방문한 걸로 쳐도 됨
            result += halfSize * halfSize;
        }
    }

    private static boolean hasDestinationInBlock(int size, int startRow, int startColumn) {
        boolean inRow = startRow <= destinationRow && destinationRow < startRow + size;
        boolean inColumn = startColumn <= destinationColumn && destinationColumn < startColumn + size;
        return inRow && inColumn;
    }
}
