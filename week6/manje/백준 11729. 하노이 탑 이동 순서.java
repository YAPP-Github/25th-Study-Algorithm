import java.util.*;
import java.io.*;

class Main {
    static StringBuilder sb = new StringBuilder();
    static int count = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        // 이동 횟수 계산: 2^n - 1
        sb.append((1 << n) - 1).append('\n');
        
        // 하노이 탑 이동 수행
        hanoi(n, 1, 2, 3);
        
        System.out.print(sb);
    }
    
    // from: 시작 기둥, aux: 보조 기둥, to: 목표 기둥
    static void hanoi(int n, int from, int aux, int to) {
        if (n == 1) {
            sb.append(from + " " + to + "\n");
            return;
        }
        
        // n-1개의 원판을 보조 기둥으로 이동
        hanoi(n-1, from, to, aux);
        
        // 가장 큰 원판을 목표 기둥으로 이동
        sb.append(from + " " + to + "\n");
        
        // n-1개의 원판을 목표 기둥으로 이동
        hanoi(n-1, aux, from, to);
    }
}
