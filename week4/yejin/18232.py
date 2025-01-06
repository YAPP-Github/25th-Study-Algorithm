import sys
from collections import defaultdict, deque
input = sys.stdin.readline

n, m = map(int, input().split())
s, e = map(int, input().split())
conn = defaultdict(list)
for _ in range(m):
    x, y = map(int, input().split())
    conn[x].append(y)
    conn[y].append(x)

visited = [0] * (n + 1)

def bfs(start):
    queue = deque([start])

    while queue:
        v = queue.popleft()
        d = [v + 1, v - 1]  # V+1이나 V-1로 이동
        if v in conn:  # V에 위치한 텔레포트와 연결된 지점으로 이동
            d += conn[v]

        for nv in d:
            if 1 <= nv <= n and visited[nv] == 0:
                queue.append(nv)
                visited[nv] = visited[v] + 1  # 이동거리 갱신

            if nv == e:
                return visited[e]

print(bfs(s))