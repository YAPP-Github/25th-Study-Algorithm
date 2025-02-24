import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        // 문자 배열
        char[] calculation = br.readLine().toCharArray();
        
        // 연산자 스택
        Stack<Character> op = new Stack<>();
        
        // 출력 배열
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < calculation.length; i++) {
            // 숫자인 경우 출력 배열에 담는다
            if(calculation[i] >= 'A' && calculation[i] <= 'Z') {
                sb.append(calculation[i] + "");
                continue;
            }
            
            // 연산자 처리
            if(calculation[i] == '(') { // 여는 괄호
                op.push(calculation[i]); // 스택에 푸시
            } else if(calculation[i] == ')') { // 닫는 괄호
                // 여는 괄호가 나올 때까지 스택에서 모든 연산자 꺼냄
                while(!op.isEmpty() && op.peek() != '(') {
                    sb.append(op.pop()); // 닫는 괄호가 아니면 연산자를 꺼내 출력 배열에 담는다
                }
                
                // 여는 괄호 제거
                if(!op.isEmpty()) {
                    op.pop();
                }
            } else { // +, -, *, /
                while(!op.isEmpty() && precedence(op.peek()) >= precedence(calculation[i])) {
                    sb.append(op.pop());
                }
                
                op.push(calculation[i]);
            }
        }
        
        // 스택에 남아있는 모든 연산자를 꺼내고 출력 배열에 담는다
        while(!op.isEmpty()) {
            sb.append(op.pop());
        }
        
        System.out.print(sb);
    }
   
    // 우선순위
    static int precedence(char op) {
        if(op == '*' || op == '/') {
            return 2;
        }
        
        if(op == '+' || op == '-') {
            return 1;
        }
        
        // 괄호 처리
        return 0;
    }
}
