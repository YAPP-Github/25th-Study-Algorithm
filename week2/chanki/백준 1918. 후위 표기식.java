import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class B1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String formula = br.readLine();
        // 연산자 처리 시 괄호 -> 곱셈, 나눗셈 -> 더하기, 빼기 순으로 처리
        Deque<String> formulaWithoutBracket = removeBracket(formula);
        System.out.println(convertToPostfixNotation(formulaWithoutBracket));
    }

    private static Deque<String> removeBracket(String formula) {
        Deque<String> stack = new ArrayDeque<>();

        // 전달받은 수식을 순회하며
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            // 만약 닫는 괄호라면
            if (c == ')') {
                Deque<String> elementsInBracket = new ArrayDeque<>();
                String last;
                // 여는 괄호가 나올 때까지 StringBuilder 저장
                while (!stack.isEmpty() && !(last = stack.removeLast()).equals("(")) {
                    elementsInBracket.addFirst(last);
                }
                // 거꾸로 꺼냈으므로 뒤집어서 전달
                // 연산자 처리 필요
                stack.add(convertToPostfixNotation(elementsInBracket));
            } else {
                stack.add(String.valueOf(c));
            }
        }

        return stack;
    }

    private static String convertToPostfixNotation(Deque<String> formula) {
        // 곱셈 나눗셈 먼저
        Deque<String> processedMultiplyAndDivide = new ArrayDeque<>();
        int formulaElementSize = formula.size();
        while (!formula.isEmpty()) {
            String element = formula.removeFirst();
            if (element.equals("*") || element.equals("/")) {
                processedMultiplyAndDivide.addLast(
                    processedMultiplyAndDivide.removeLast() + formula.removeFirst() + element
                );
            } else {
                processedMultiplyAndDivide.addLast(element);
            }
        }

        // 더하기 빼기 처리
        Deque<String> result = new ArrayDeque<>();
        while (!processedMultiplyAndDivide.isEmpty()) {
            String element = processedMultiplyAndDivide.removeFirst();
            if (element.equals("+") || element.equals("-")) {
                result.addLast(result.removeLast() + processedMultiplyAndDivide.removeFirst() + element);
            } else {
                result.addLast(element);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String s : result) {
            sb.append(s);
        }
        return sb.toString();
    }
}
