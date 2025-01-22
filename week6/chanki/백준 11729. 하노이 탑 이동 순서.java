import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B11729 {

    private static StringBuilder sb = new StringBuilder();
    private static int totalMovementCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfRing = Integer.parseInt(br.readLine());
        moveHanoiRing(numberOfRing, 1, 3);
        System.out.println(totalMovementCount);
        System.out.println(sb);
    }

    // 하노이탑 이동
    // ex) 7개를 이동시키는 경우
    // = 6개를 2번 기둥으로 이동 + 제일 큰 거를 3번 기둥으로 이동 + 2번 기둥의 6개를 3번 기둥으로 이동
    // 6개를 이동 = 5개를 2번 기둥 + ...
    // 해당 구조를 재귀로 표현
    private static void moveHanoiRing(int numberOfRing, int fromStickNumber, int destinationStickNumber) {
        if (numberOfRing == 1) {
            sb.append(fromStickNumber + " " + destinationStickNumber).append("\n");
            totalMovementCount++;
            return;
        }

        int leftStickNumber = 6 - (fromStickNumber + destinationStickNumber);
        moveHanoiRing(numberOfRing - 1, fromStickNumber, leftStickNumber);
        sb.append(fromStickNumber + " " + destinationStickNumber).append("\n");
        totalMovementCount++;
        moveHanoiRing(numberOfRing - 1, leftStickNumber, destinationStickNumber);
    }
}
