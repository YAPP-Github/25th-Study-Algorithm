import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P150368 {

    private int[][] users;
    private int[] emoticons;
    private double[] discountRate = new double[]{0.1, 0.2, 0.3, 0.4};
    private int[] results = new int[]{0, 0};
    private Set<Integer> visited = new HashSet<>();

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;

        int[] discountRateIndexByEmoticon = new int[emoticons.length];
        Arrays.fill(discountRateIndexByEmoticon, 0);

        // 브루트포스
        // 유저는 최대 100명, 이모티콘 개수 최대 7개, 비율의 종류는 4가지
        // 각 이모티콘별 나올 수 있는 비율의 모든 조합은 최대 4^7 = 16,384
        // 이때 100명의 모든 결과값을 계산한다고 해도 100 * 4^7 = 1,638,400
        dfs(discountRateIndexByEmoticon);

        return results;
    }

    private void dfs(int[] discountRateIndexByEmoticon) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(discountRateIndexByEmoticon).forEach(sb::append);
        int combination = Integer.parseInt(sb.toString());
        if (visited.contains(combination)) {
            return;
        }
        // 방문했던 거 기록
        visited.add(combination);

        int emoticonPlusRegisterNumber = 0;
        int accumulatedSalesMoney = 0;

        for (int user = 0; user < users.length; user++) {
            double discountRateStandard = (double) users[user][0] / 100;
            double sum = 0;
            for (int i = 0; i < discountRateIndexByEmoticon.length; i++) {
                // 유저가 이모티콘을 구매 안 하는 조건
                if (discountRateStandard > discountRate[discountRateIndexByEmoticon[i]]) {
                    continue;
                }

                sum += emoticons[i] * (1 - discountRate[discountRateIndexByEmoticon[i]]);
            }

            // 이모티콘 플러스를 구매하는 기준
            if (sum >= users[user][1]) {
                emoticonPlusRegisterNumber++;
                continue;
            }
            // 이모티콘 플러스 구매 안 하면 그냥 이모티콘 사야지
            accumulatedSalesMoney += sum;
        }

        // 이모티콘 플러스 구매한다는 사람이 기존보다 같거나 많고, 판매액도 같거나 많으면 결과값을 바꾼다
        if (emoticonPlusRegisterNumber > results[0]) {
            results[0] = emoticonPlusRegisterNumber;
            results[1] = accumulatedSalesMoney;
        } else if (emoticonPlusRegisterNumber == results[0] && accumulatedSalesMoney >= results[1]) {
            results[0] = emoticonPlusRegisterNumber;
            results[1] = accumulatedSalesMoney;
        }

        // 하위 탐색을 또 진행한다
        for (int i = 0; i < discountRateIndexByEmoticon.length; i++) {
            if (discountRateIndexByEmoticon[i] >= 3) {
                continue;
            }

            // 모든 경우의 수 탐색을 위한 처리
            discountRateIndexByEmoticon[i] += 1;
            dfs(discountRateIndexByEmoticon);
            discountRateIndexByEmoticon[i] -= 1;
        }
    }
}
