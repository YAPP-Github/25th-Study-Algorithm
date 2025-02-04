import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B6236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineInputs = br.readLine().split(" ");
        int numberOfDay = Integer.parseInt(firstLineInputs[0]);
        int withdrawalCount = Integer.parseInt(firstLineInputs[1]);

        List<Integer> moneyUsingEachDay = new ArrayList<>();
        int maxMoney = 0;
        long sumOfMoney = 0;
        for (int i = 0; i < numberOfDay; i++) {
            int money = Integer.parseInt(br.readLine());
            moneyUsingEachDay.add(money);
            maxMoney = Math.max(maxMoney, money);
            sumOfMoney += money;
        }

        // 제일 큰 금액을 쓰는 날을 처리하기 위해선 최소값이 해당 금액만큼은 되어야 한다.
        long left = maxMoney;
        // 모든 날의 금액을 합친 것보다 클 수는 없다.
        long right = sumOfMoney;
        long mid;
        long result = sumOfMoney;

        while (left <= right) {
            mid = left + (right - left) / 2;
            // 처리가 가능하면, 최소값을 갱신하고 금액을 줄여서 재탐색
            if (canProcess(moneyUsingEachDay, mid, withdrawalCount)) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                // 처리가 불가하면 금액을 키워서 재탐색
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    private static boolean canProcess(List<Integer> moneyUsingEachDay, long withdrawalMoney, int maxWithdrawalCount) {
        int withdrawalCount = 0;
        long holdingCash = 0;
        for (int i = 0; i < moneyUsingEachDay.size(); i++) {
            int money = moneyUsingEachDay.get(i);

            // 가진 돈으로 지출 가능
            if (holdingCash >= money) {
                holdingCash -= money;
                continue;
            }

            // 가진 돈으로는 부족 & 새로 돈 인출하면 처리 가능
            if (money <= withdrawalMoney) {
                withdrawalCount++;
                holdingCash = withdrawalMoney - money;
            } else {
                return false;
            }
        }

        return withdrawalCount <= maxWithdrawalCount;
    }
}
