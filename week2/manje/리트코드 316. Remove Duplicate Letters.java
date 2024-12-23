import java.util.*;

class Solution {
    public String removeDuplicateLetters(String s) {
        // 1. 각 문자의 마지막 등장 위치를 저장하는 맵
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence.put(s.charAt(i), i);
        }

        // 2. 스택을 사용하여 결과 문자열 구성
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[26]; // 스택에 해당 문자가 있는지 여부
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // 이미 스택에 있는 문자면 스킵
            if (inStack[currentChar - 'a']) {
                continue;
            }

            // 스택의 top에 있는 문자가 현재 문자보다 사전 순서상 뒤이고,
            // 해당 문자가 나중에 다시 나타난다면 스택에서 제거
            while (!stack.isEmpty() && stack.peek() > currentChar && lastOccurrence.get(stack.peek()) > i) {
                inStack[stack.pop() - 'a'] = false;
            }

            // 스택에 현재 문자 추가 및 스택에 들어왔음을 표시
            stack.push(currentChar);
            inStack[currentChar - 'a'] = true;
        }

        // 3. 스택에 있는 문자들을 순서대로 결합하여 결과 문자열 생성
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
