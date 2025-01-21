import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B1629 {

    // 메모이제이션을 위한 변수
    static Map<Integer, Long> remainderByPower = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineInput = br.readLine().split(" ");
        int base = Integer.parseInt(firstLineInput[0]);
        int power = Integer.parseInt(firstLineInput[1]);
        int modulus = Integer.parseInt(firstLineInput[2]);

        System.out.println(divideAndConquer(base, power, modulus));
    }

    /**
     * 분할 정복
     * 가령 (Base 11 12) 가 입력으로 들어왔다고 하자
     * 나머지 정리에 따라 A^x = Q(x)*x + R(x) 라고 한다면
     * A^5 = [Q(5)*12 + R(5)]
     * A^6 = A*[Q(5)*12 + R(5)] = A*Q(5)*12 + A*R(5)
     * A^11 = A^5 * A^6 = [Q(5)*12 + R(5)] * [A*Q(5)*12 + A*R(5)] = ...+ R(5) * A * R(5)
     * R(5) * A * R(5) 항을 제외하고 나머지는 모두 12의 배수
     * 따라서 A^11 % 12 는 A^5, A^6 각각의 나머지를 곱한 뒤 다시 나머지를 구한 것과 동일
     */
    private static long divideAndConquer(int base, int power, int modulus) {
        if (power <= 1) {
            return (int) Math.pow(base, power) % modulus;
        }

        if (remainderByPower.containsKey(power)) {
            return remainderByPower.get(power);
        }

        long remainderA = divideAndConquer(base, power / 2, modulus);
        long remainderB = divideAndConquer(base, power - power / 2, modulus);

        long remainder = (remainderA * remainderB) % modulus;
        remainderByPower.put(power, remainder);
        return remainder;
    }
}
