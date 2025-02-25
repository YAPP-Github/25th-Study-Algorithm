// 일렬로 나열된 n개 집에 택배 배달
// i번째 집은 물류창고에서 거리 i만큼 떨어져 있음, i와 j번째 집은 j-i만큼 ㅇㅇ
// 트럭에 상자를 최대 cap개 실을 수 있음
// 상자들 실어서 물류창고에서 출발해 각 집에 배달하면서, 빈 상자 수거해 물류창고에서 내림
// 트럭 하나로 모든 배달과 수거 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리는?
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int del = 0; int pick = 0;

        // 마지막 집부터 시작
        for (int i=n-1; i>=0; i--) {
            // 현재 집에 몇 번 방문해야 하는지 측정
            if (deliveries[i]>0 || pickups[i]>0) {
                int cnt = 0;

                while (del<deliveries[i] || pick<pickups[i]) {
                    cnt+=1;
                    del += cap;
                    pick += cap;
                }

                // 현재 집에 배달, 수거하고
                // 추가로 배달, 수거 가능한 상자 개수 구하기
                del -= deliveries[i];
                pick -= pickups[i];
                answer += (i+1)*cnt*2;
            }
        }
        return answer;
    }
}
