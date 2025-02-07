import java.util.Arrays;

public class P43105 {
    public int solution(int[][] triangle) {
        /**
         * 배열을 각 경로의 최대누적값으로 갱신한다
         *     7
         *    3 8
         *   8 1 0
         * 이면
         *     7
         *   10 15
         * 18 16 15
         * 으로 누적해나간다.
         * 마지막 행의 최대값이 특정 경로의 최대값이다.
         */
        for (int row = 1; row < triangle.length; row++) {
            for (int column = 0; column < triangle[row].length; column++) {
                if (column == 0) {
                    triangle[row][0] += triangle[row - 1][0];
                } else if (column == triangle[row].length - 1) {
                    triangle[row][column] += triangle[row - 1][column - 1];
                } else {
                    triangle[row][column] += Math.max(triangle[row - 1][column - 1], triangle[row - 1][column]);
                }
            }
        }

        return Arrays.stream(triangle[triangle.length - 1]).max().orElse(0);
    }
}
