import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2281 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineInputs = br.readLine().split(" ");
        int numberOfPerson = Integer.parseInt(firstLineInputs[0]);
        int noteLength = Integer.parseInt(firstLineInputs[1]);

        int[] lengthOfNames = new int[numberOfPerson + 1];
        for (int i = 1; i <= numberOfPerson; i++) {
            lengthOfNames[i] = Integer.parseInt(br.readLine());
        }

        int[] minimumResultsInEachNumberOfName = new int[numberOfPerson + 1];
        /**
         * 역순으로 배치하는 접근
         * N = 5라면
         * minimumResultsInEachNumberOfName[5] = (5번을 배치했을 때의 최소값)
         * minimumResultsInEachNumberOfName[4] = (4, 5번을 배치했을 때의 최소값)
         * minimumResultsInEachNumberOfName[3] = (3, 4, 5번을 배치했을 때의 최소값)
         *
         * minimumResultsInEachNumberOfName[3]은
         * 3, 4, 5를 모두 잇는 경우, 5번만 개행하는 경우, 4, 5번을 개행하는 경우가 있음
         * 5번을 개행하는 경우 -> 3, 4 최소값 계산 + minimumResultsInEachNumberOfName[5]
         * 4, 5번을 개행하는 경우 -> 3 최소값 계산 + minimumResultsInEachNumberOfName[4]
         */
        for (int target = numberOfPerson; target > 0; target--) {
            int min = Integer.MAX_VALUE;

            // 대상부터 끝까지 한 줄 안에 들어갈 수 있는지 판단하여, 한 줄 안에 들어가면 최솟값 변경
            int sum = lengthOfNames[target];
            for (int i = target + 1; i <= numberOfPerson && sum <= noteLength; i++) {
                sum += 1 + lengthOfNames[i];
            }
            if (sum <= noteLength) {
                minimumResultsInEachNumberOfName[target] = 0;
                continue;
            }

            // 한 줄에 모든 원소를 포함하는 경우를 시작으로, 맨 뒤에서부터 하나씩 다음 줄로 개행시키는 경우를 계산
            for (int targetLineLastPerson = numberOfPerson; targetLineLastPerson >= target; targetLineLastPerson--) {
                int value = lengthOfNames[target];
                for (int i = target + 1; i <= targetLineLastPerson && value <= noteLength; i++) {
                    value += 1 + lengthOfNames[i];
                }
                // 한 줄에 배치할 수 없는 경우라면 패스
                if (value > noteLength) {
                    continue;
                }

                // 배치할 수 있는 경우에 대해 최솟값 갱신 시도
                min = Math.min(
                    min,
                    (int) Math.pow(noteLength - value, 2) + minimumResultsInEachNumberOfName[targetLineLastPerson + 1]
                );
            }

            minimumResultsInEachNumberOfName[target] = min;
        }

        System.out.println(minimumResultsInEachNumberOfName[1]);
    }
}
