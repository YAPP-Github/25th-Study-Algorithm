import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfTestCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfTestCase; i++) {
            int numberOfDate = Integer.parseInt(br.readLine());
            String[] inputs = br.readLine().split(" ");
            int[] stockValues = Arrays.stream(inputs)
                .mapToInt(Integer::parseInt)
                .toArray();
            // 특정 일자에 제품을 구매하는 경우 팔아야 하는 가격을 저장
            // 역순으로 최대값을 저장
            // 가령, 원 주식 가격이 일자 별로 1 1 3 1 2 라면, 3 3 3 2 2를 저장
            int[] maxValueAfterDate = new int[numberOfDate];
            int maxValue = 0;
            for (int date = numberOfDate - 1; date >= 0; date--) {
                maxValue = Math.max(maxValue, stockValues[date]);
                maxValueAfterDate[date] = maxValue;
            }

            long ownStockValue = 0;
            int ownStockCount = 0;
            long benefit = 0;
            for (int date = 0; date < numberOfDate - 1; date++) {
                // 만약 오늘자 주식 가격이 최대값에 미치지 못했다면 구매
                if (stockValues[date] < maxValueAfterDate[date]) {
                    ownStockValue += stockValues[date];
                    ownStockCount++;
                // 오늘자 주식 가격이 최대값과 같다면, 전부 매도
                } else if (stockValues[date] == maxValueAfterDate[date]) {
                    benefit += (stockValues[date] * ownStockCount - ownStockValue);
                    ownStockValue = 0;
                    ownStockCount = 0;
                }
            }

            // 마지막날에 가지고 있는 주식이 있다면, 모두 첨부
            if (ownStockValue > 0) {
                benefit += (stockValues[stockValues.length - 1] * ownStockCount - ownStockValue);
            }

            sb.append(benefit).append("\n");
        }

        System.out.println(sb);
    }
}
