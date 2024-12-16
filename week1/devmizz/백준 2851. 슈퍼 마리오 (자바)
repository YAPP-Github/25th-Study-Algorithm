import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class B2851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        int sum = 0;
        // 빈 줄이 나올 때까지 입력 받기
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            int number = Integer.parseInt(line);

            // 현재 누적 점수와 100의 차이 vs 다음으로 이동했을 때의 누적점수와 100의 차이
            // 같은 경우는 다음 버섯을 먹어야 하므로 등호 제외
            if (Math.abs(100 - sum) < Math.abs(100 - (sum + number))) {
                // 만약 다음보다 지금까지의 누적이 점수차가 더 적다면 끝!
                break;
            }

            // 다음 버섯으로 이동
            sum += number;
        }

        System.out.println(sum);
    }
}
