n = int(input())
level = sorted(list(map(int, input().split())))
result = 0  # 획득한 골드

while len(level) > 1:
    c1, c2 = level.pop(), level.pop()
    level.append(c1)  # 두 카드 중 최댓값
    result += c1 + c2

print(result)