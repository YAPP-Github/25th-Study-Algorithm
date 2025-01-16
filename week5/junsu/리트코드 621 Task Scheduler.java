class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char task : tasks) {
            freq[task - 'A']++;
        }

        // 우선순위 큐에 빈도 수를 높은 순으로 삽입
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int count : freq) {
            if(count > 0) {
                pq.add(count);
            }
        }

        int totalIntervals = 0;
        while(!pq.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int cycles = 0;

            // n + 1 만큼 순환
            for(int i = 0; i <= n; i++) {
                if(!pq.isEmpty()) {
                    temp.add(pq.poll() - 1);
                    cycles++;
                }
            }

            // 남아있으면 다시 삽입
            for(int remaining : temp) {
                if(remaining > 0) {
                    pq.add(remaining);
                }
            }

            totalIntervals += pq.isEmpty() ? cycles : n + 1;
        }

        return totalIntervals;
    }
}