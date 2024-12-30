import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class B15787 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLineSplit = br.readLine().split(" ");

        int[] train = new int[Integer.parseInt(firstLineSplit[0])];

        for (int i = 0; i < Integer.parseInt(firstLineSplit[1]); i++) {
            String[] commandSplit = br.readLine().split(" ");
            int commandType = Integer.parseInt(commandSplit[0]);

            int carIndex = Integer.parseInt(commandSplit[1]) - 1;
            // 비트연산을 이용
            // 1. 각 좌석의 유일성을 보장한다.
            // 2. 착석 여부를 0, 1로 구분한다.
            // 1010과 1110을 예시로
            if (commandType == 1) {
                // 3번째 자리 착석 -> 1010 | 0100 -> 1110
                // 3번째 자리 착석 -> 1110 | 0100 -> 1110 (이미 앉아 있을 때 영향 X)
                train[carIndex] |= (1 << (Integer.parseInt(commandSplit[2]) - 1));
            } else if (commandType == 2) {
                // 3번째 자리 하차 -> 1010 & ~(1 << 2) -> 1010 & 1011 -> 1010 (이미 비어있는 자리여도 영향 X)
                // 3번째 자리 하차 -> 1110 & 1011 -> 1010
                train[carIndex] &= ~(1 << (Integer.parseInt(commandSplit[2]) - 1));
            } else if (commandType == 3) {
                // 1010 앞으로 이동 -> 10100 & 1111 -> 0100 (맨 앞사람은 하차한 효과)
                train[carIndex] = (train[carIndex] << 1) & ((1 << 20) - 1);
            } else {
                // 1010 뒤로 이동 -> 101 & 1111 -> 101 (하차할 사람 없음)
                train[carIndex] = (train[carIndex] >> 1) & ((1 << 20) - 1);
            }
        }

        int answer = 0;
        // 2진수를 구성하는 모든 값이 겹쳐야 10진수도 중복된다.
        // 따라서 비트 연산의 결과값은 정확히 동일한 2진수 비트 구성을 가져야만 중복된다.
        Set<Integer> duplicateCarSeatsCheckList = new HashSet<>();
        for (int car : train) {
            if (duplicateCarSeatsCheckList.contains(car)) {
                continue;
            }

            duplicateCarSeatsCheckList.add(car);
            answer++;
        }

        System.out.println(answer);
    }
}
