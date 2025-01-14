import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

public class B15720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLineInput = br.readLine().split(" ");
        int numberOfBurger = Integer.parseInt(firstLineInput[0]);
        int numberOfSide = Integer.parseInt(firstLineInput[1]);
        int numberOfDrink = Integer.parseInt(firstLineInput[2]);

        // 비싼 순으로 주문을 뽑아내기 위한 priority queue
        PriorityQueue<Integer> burgers = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> sides = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> drinks = new PriorityQueue<>(Comparator.reverseOrder());
        Arrays.stream(br.readLine().split(" ")).forEach(it -> burgers.add(Integer.parseInt(it)));
        Arrays.stream(br.readLine().split(" ")).forEach(it -> sides.add(Integer.parseInt(it)));
        Arrays.stream(br.readLine().split(" ")).forEach(it -> drinks.add(Integer.parseInt(it)));

        int notDiscountedResult = 0;
        int result = 0;
        while (!(burgers.isEmpty() && sides.isEmpty() && drinks.isEmpty())) {
            int burger = Optional.ofNullable(burgers.poll()).orElseGet(() -> 0);
            int side = Optional.ofNullable(sides.poll()).orElseGet(() -> 0);
            int drink = Optional.ofNullable(drinks.poll()).orElseGet(() -> 0);

            notDiscountedResult += burger + side + drink;

            // 셋 중 하나라도 없는 경우 할인을 적용하지 않는다.
            if (burger == 0 || side == 0 || drink == 0) {
                result += burger + side + drink;
                continue;
            }

            result += (int) ((burger + side + drink) * 0.9);
        }

        System.out.println(notDiscountedResult);
        System.out.println(result);
    }
}
