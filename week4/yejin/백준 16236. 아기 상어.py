from collections import deque

n = int(input())
graph = []  # 공간의 상태
for i in range(n):
    tmp = list(map(int, input().split()))
    for j in range(n):
        if tmp[j] == 9:
            x, y = i, j  # 현재 아기 상어의 위치(x, y)
    graph.append(tmp)

dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]

def bfs(x, y, size):
    visited = [[0] * n for _ in range(n)]
    queue = deque([(x, y)])
    fish = []  # 먹을 수 있는 물고기

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
                if graph[nx][ny] <= size:
                    queue.append((nx, ny))  # 아기 상어 이동
                    visited[nx][ny] = visited[x][y] + 1
                    if graph[nx][ny] < size and graph[nx][ny] != 0:  # 빈 칸이 아니고, 자신의 크기보다 작은 물고기인 경우
                        fish.append((nx, ny, visited[nx][ny]))  # 물고기의 위치, 아기 상어로부터의 거리(지나야 하는 칸의 개수)

    return sorted(fish, key=lambda x: (x[2], x[0], x[1]))  # 거리가 가까운 물고기 - 가장 위에 있는 물고기 - 가장 왼쪽에 있는 물고기 순으로 정렬

size = 2  # 아기 상어의 크기
result, count = 0, 0  # 아기 상어의 이동 시간, 아기 상어가 먹은 물고기의 개수

while True:
    fish = bfs(x, y, size)

    if len(fish) == 0:  # 더 이상 먹을 수 있는 물고기가 공간에 없다면
        print(result)
        break

    nx, ny, move_time = fish[0]

    result += move_time  # 물고기를 먹을 때까지 이동한 만큼 시간 카운트
    graph[x][y], graph[nx][ny] = 0, 0  # 아기 상어가 있던 자리와 물고기를 먹은 자리는 빈 칸으로
    x, y = nx, ny  # 아기 상어의 현재 좌표 이동
    count += 1

    if size == count:  # 자신의 크기와 같은 수의 물고기를 먹을 때
        count = 0
        size += 1
