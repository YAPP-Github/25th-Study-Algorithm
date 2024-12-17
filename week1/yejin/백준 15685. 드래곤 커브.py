graph = [[0] * 101 for _ in range(101)]
dir = [1, 2, 3, 0]  # 0 오른쪽, 1 위쪽, 2 왼쪽, 3 아래쪽 -> 90도 회전한 방향
dx, dy = [1, 0, -1, 0], [0, -1, 0, 1]  # 0 오른쪽, 1 위쪽, 2 왼쪽, 3 아래쪽

for _ in range(int(input())):
    x, y, d, g = map(int, input().split())
    moves = [(x, y, d)]
    graph[x][y] = 1
    nx, ny = x + dx[d], y + dy[d]
    graph[nx][ny] = 1

    for _ in range(g):
        tmp = []
        for i in range(len(moves) - 1, -1, -1):  # 추가된 선분의 역순으로 진행
            d = dir[moves[i][2]]  # 90도 회전한 방향
            tmp.append((nx, ny, d))  # 새로운 드래곤 커브 추가
            nx += dx[d]
            ny += dy[d]
            if nx < 0 or nx >= 101 or ny < 0 or ny >= 101:  # 격자를 벗어난 경우 제외
                tmp.pop()
                continue
            graph[nx][ny] = 1

        moves.extend(tmp)

result = 0
for i in range(100):
    for j in range(100):
        # 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형
        if graph[i][j] == 1 and graph[i + 1][j] == 1 and graph[i][j + 1] == 1 and graph[i + 1][j + 1] == 1:
            result += 1
print(result)
