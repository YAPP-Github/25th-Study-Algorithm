public class P68936 {

    int[] result = new int[2];
    int[][] arr;

    public int[] solution(int[][] arr) {
        this.arr = arr;
        divideAndConquer(0, 0, arr.length);
        return result;
    }

    private void divideAndConquer(int startRow, int startColumn, int size) {
        // 블록 영역 내에 같은 숫자만 있으면 분할할 필요 없음
        if (hasSameNumberInBlock(startRow, startColumn, size)) {
            result[arr[startRow][startColumn]] += 1;
            return;
        }

        // 네 영역으로 분할
        divideAndConquer(startRow, startColumn, size / 2);
        divideAndConquer(startRow + size / 2, startColumn, size / 2);
        divideAndConquer(startRow, startColumn + size / 2, size / 2);
        divideAndConquer(startRow + size / 2, startColumn + size / 2, size / 2);
    }

    // 블록 영역 내에 같은 숫자만 있는지 판단
    private boolean hasSameNumberInBlock(int startRow, int startColumn, int size) {
        int value = arr[startRow][startColumn];

        for (int row = startRow; row < startRow + size; row++) {
            for (int column = startColumn; column < startColumn + size; column++) {
                if (arr[row][column] != value) {
                    return false;
                }
            }
        }

        return true;
    }
}
