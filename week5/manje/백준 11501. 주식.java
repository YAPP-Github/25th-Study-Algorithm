import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            
            // 배열에 주가 저장
            int[] prices = new int[N];
            for(int j = 0; j < N; j++){
                prices[j] = Integer.parseInt(st.nextToken());
            }
            
            // 뒤에서부터 탐색하면서 최대 이익 계산
            long maxProfit = 0;  // 최대 이익
            int maxPrice = prices[N-1];  // 현재까지의 최대 가격
            
            for(int j = N-2; j >= 0; j--){
                if(prices[j] < maxPrice){
                    maxProfit += maxPrice - prices[j];
                }
                maxPrice = Math.max(maxPrice, prices[j]);
            }
            
            System.out.println(maxProfit);
        }
    }
}
