import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        // 2^N 크기 계산
        int size = 1 << n;
       
        System.out.print(findOrder(size, r, c));
    }
    
    // size: 현재 사분면의 한 변 길이, r: 행, c: 열 
    static int findOrder(int size, int r, int c) {
        if(size == 1){
            return 0;
        }
       
        int half = size/2;
        int area = half * half;
       
        // 1사분면
        if(r < half && c < half){
            return findOrder(half, r, c);
        }
        // 2사분면 
        if(r < half && c >= half){
            return area + findOrder(half, r, c-half);
        }
        // 3사분면
        if(r >= half && c < half){
            return 2*area + findOrder(half, r-half, c);
        }
        // 4사분면
        return 3*area + findOrder(half, r-half, c-half);
    }
}
