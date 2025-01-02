n = int(input())
participant = [list(map(int, input().split())) for _ in range(n)]

# 해결한 문제의 개수가 더 많은 참가자, 패널티 총합이 더 작은 참가자 -> 높은 순위
participant.sort(key=lambda x: (-x[0], x[1]))

count = 0
for i in range(5, n):
    if participant[4][0] == participant[i][0]:
        count += 1
    else:
        break

print(count)
