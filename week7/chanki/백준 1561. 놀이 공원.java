import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B1561 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLineInputs = br.readLine().split(" ");
        int numberOfChildren = Integer.parseInt(firstLineInputs[0]);
        int numberOfRide = Integer.parseInt(firstLineInputs[1]);
        int[] playtimeOfEachRide = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        if (numberOfChildren <= numberOfRide) {
            System.out.println(numberOfChildren);
            return;
        }

        long left = 1;
        // 20억명이 30시간이 소요되는 1대의 놀이기구를 이용하는 경우 최대 시간 소요 (20억 * 30 = 600억)
        long right = 60000000000L;
        long mid;
        long minPlaytime = right;
        long numberOfRider = 0;

        // 해당 인원을 소화하는 최소 시간 이분 탐색
        while (left <= right) {
            mid = left + (right - left) / 2;

            long sumOfRider = 0;
            for (int time : playtimeOfEachRide) {
                sumOfRider += mid / time + 1;
            }

            if (sumOfRider >= numberOfChildren) {
                if (mid < minPlaytime) {
                    numberOfRider = sumOfRider;
                    minPlaytime = mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 해당 시간에 탑승한 인원 - 원래 탑승해야 하는 인원 = 남는 인원
        long numberOfLeftRider = numberOfRider - numberOfChildren;
        for (int i = numberOfRide - 1; i >= 0; i--) {
            if (minPlaytime % playtimeOfEachRide[i] == 0) {
                // 남는 인원만큼을 제외하고 되돌아가면(놀이기구 뒷순서부터 앞으로 조회) 정답
                if (numberOfLeftRider-- == 0) {
                    System.out.println(i + 1);
                    break;
                }
            }
        }
    }
}
