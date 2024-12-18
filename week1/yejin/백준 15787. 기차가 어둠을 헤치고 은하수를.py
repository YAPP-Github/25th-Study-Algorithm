import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())
trains = [deque([0] * 20) for _ in range(n)]

for _ in range(m):
    c = list(map(int, input().split()))
    if c[0] == 1:
        trains[c[1] - 1][c[2] - 1] = 1
    elif c[0] == 2:
        trains[c[1] - 1][c[2] - 1] = 0
    elif c[0] == 3:
        trains[c[1] - 1].rotate(1)  # 한 칸씩 뒤로
        trains[c[1] - 1][0] = 0
    elif c[0] == 4:
        trains[c[1] - 1].rotate(-1)  # 한 칸씩 앞으로
        trains[c[1] - 1][-1] = 0

result = []
count = 0
for t in trains:
    if t not in result:
        result.append(t)
        count += 1
print(count)
