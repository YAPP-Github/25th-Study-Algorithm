/*
  버섯 점수를 차례로 더하면서 합을 아래와 같은 케이스에 대해 순서대로 비교하고 출력
  1. 100일 때
  2. 100보다 작지만 다음 연산 시 100보다 클 때
  3. 100보다 클 때  
*/
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[10]; // 버섯 점수 배열
        int sum = 0; // 버섯 점수 합
        
        // 버섯 배열에 점수 넣기
        for(int i = 0; i < 10; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        // 버섯 점수 합 비교
        for(int i = 0; i < 10; i++){
            sum += arr[i];
            // 100일 때
            if(sum == 100){
                break;
            }
            if(i < 9){ // i가 9일 경우 배열 크기를 넘기 때문에 다음 연산을 할 수가 없음
                // 100보다 작지만 다음 연산 시 100보다 클 때
                if((sum < 100) && (sum + arr[i+1] > 100)){
                    if((100 - sum) < (sum + arr[i+1] - 100)){
                        break;
                    }
                    if((100 - sum) == (sum + arr[i+1] - 100)){
                        sum += arr[i+1];
                        break;
                    }
                }
            }
            // 100보다 클 때
            if(sum > 100){
                break;
            }
        }
        System.out.print(sum);
    }
}
