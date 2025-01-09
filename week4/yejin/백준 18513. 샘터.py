from collections import deque

n, k = map(int, input().split())
loc = list(map(int, input().split()))

queue = deque()
visited = set()
for l in loc:
    queue.append((l, 1))  # 샘터의 위치, 불행도
    visited.add(l)  # 방문 체크

result, cnt = 0, 0  # 불행도의 합, 현재까지 지어진 집의 개수
while queue:
    x, u = queue.popleft()

    for i in [-1, 1]:  # 샘터, 집을 기준으로 양옆으로 이동하면서 집 짓기
        nx = x + i
        if nx in visited:
            continue

        queue.append((nx, u + 1))  # 집 위치, 불행도 + 1
        visited.add(nx)  # 해당 위치에 집 짓기
        cnt += 1
        result += u

        if cnt == k:
            print(result)
            exit()