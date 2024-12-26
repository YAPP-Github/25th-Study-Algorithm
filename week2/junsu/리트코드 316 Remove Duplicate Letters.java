import java.util.*;

class Solution {
    public String removeDuplicateLetters(String s) {
        int[] alphabet = new int[26];

        for(char c : s.toCharArray()) {
            alphabet[c - 'a']++;
        }

        boolean[] inStack = new boolean[26];
        Deque<Character> stack =new ArrayDeque<>();

        for(char c : s.toCharArray()) {
            alphabet[c - 'a']--;

            // 이미 스택에 존재하면 패스
            if(inStack[c - 'a']) {
                continue;
            }

            // 스택이 차있고, 스택의 가장 위의 값이 현재 문자보다 크고, 이후에도 해당 문자가 들어올 수 있으면
            // stack에서 제거한다.
            while(!stack.isEmpty() && stack.peek() > c && alphabet[stack.peek() - 'a'] > 0) {
                inStack[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            inStack[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        return sb.toString();
    }
}
