import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 소의 번호를 인덱스로 하는 소의 위치 배열
        int[] arr = new int[11];
        // 소의 위치 배열에 기본값 세팅
        for(int i = 0; i < 11; i++){
            arr[i] = Integer.MIN_VALUE;
        }
        int result = 0;
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            // 관찰한 소의 번호
            int num = Integer.parseInt(st.nextToken());
            // 관찰한 소의 위치
            int po = Integer.parseInt(st.nextToken());      
            // 관찰한 소의 위치가 이전 위치와 다르면서 0이나 1일 경우
            if((arr[num] != po && arr[num] != Integer.MIN_VALUE) && (po == 0 || po == 1)){
                result++;
            }
            arr[num] = po;
        }
        System.out.print(result);
    }
}
