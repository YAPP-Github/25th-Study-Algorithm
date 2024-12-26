import java.util.Stack;

// leetcode-316: Remove Duplicate Letters
// 중복을 제거했을 때 나오는 단어중 가장 빠른 단어
public class LC_316 {
    public static void main(String[] args) {
        String input = "cbacdcbc";
        System.out.println(removeDuplicateLetters(input));
    }

    public static String removeDuplicateLetters(String s) {
        int[] cnt = new int[26]; // 등장 횟수
        boolean[] visited = new boolean[26]; // 중복방지용
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()) {
            cnt[c-'a']++;
        }

        for (char c: s.toCharArray()) {
            int idx = c-'a';
            cnt[idx]--;
            if (visited[idx]) {
                continue;
            }

            // 탑이 현재 문자보다 크고, 다시 뒤에 등장한다면 pop
            // 그래야 더 작은 순서 유지 가능
            while (!stack.isEmpty() && stack.peek()>c && cnt[stack.peek()-'a']>0) {
                visited[stack.pop()-'a'] = false;
            }
            stack.push(c);
            visited[idx] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (char c: stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
