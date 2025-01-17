import sys
input = sys.stdin.readline

n = int(input())
times = [list(map(int, input().split())) for _ in range(n)]
times.sort(key=lambda x: (x[1], x[0]))  # 끝나는 시간이 빠른 순 - 시작 시간이 빠른 순으로 정렬 (빨리 끝나는 회의부터 선택해야 최대한 많은 회의를 할 수 있다)

result, cur = 0, 0  # 회의 최대 개수, 현재 시간
for s, e in times:
    if s >= cur:  # 회의 시작 시간이 현재 시간보다 뒤라면
        result += 1  # 회의 추가
        cur = e  # 현재 시간을 회의 종료 시간으로 갱신

print(result)