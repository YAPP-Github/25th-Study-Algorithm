import sys
from collections import deque
input = sys.stdin.readline

MAX_LEN = 100000

n, k = map(int, input().split())
visited = [-1] * (MAX_LEN + 1)  # 0으로 초기화하면 순간이동의 경우 무한으로 탐색하게 됨

def bfs(start):
    queue = deque([start])
    visited[start] = 0

    while queue:
        x = queue.popleft()
        if x == k:
            return visited[k]

        for nx in (2 * x, x - 1, x + 1):
            if 0 <= nx <= MAX_LEN and visited[nx] == -1:
                if nx == 2 * x:
                    queue.appendleft(nx)  # 순간이동이므로 appendleft()로 먼저 탐색하도록
                    visited[nx] = visited[x]  # 0초 후에 2*X의 위치로 이동
                else:
                    queue.append(nx)
                    visited[nx] = visited[x] + 1  # 1초 후에 X-1 또는 X+1로 이동

print(bfs(n))