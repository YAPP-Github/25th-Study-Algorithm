import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            
            // 원본 배열 오름차순 정렬
            Arrays.sort(arr);
            
            // 정렬될 배열의 왼쪽, 오른쪽 각각 작은 수부터 채우기
            int[] result = new int[n];
            int left = 0;
            int right = n-1;
            for(int j = 0; j < n; j++) {
                if(j % 2 == 0) {
                    result[left++] = arr[j];
                } else {
                    result[right--] = arr[j];
                }
            }
            
            // 최대 차이 계산
            int answer = 0;
            for(int j = 1; j < n; j++) {
                answer = Math.max(answer, Math.abs(result[j] - result[j-1]));
            }
            
            // 첫 번째와 마지막 통나무의 높이 차이도 확인
            answer = Math.max(answer, Math.abs(result[n-1] - result[0]));
            
            System.out.println(answer);
        }
    }
}
