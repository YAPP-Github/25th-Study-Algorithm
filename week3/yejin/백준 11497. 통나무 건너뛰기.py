for _ in range(int(input())):
    n = int(input())
    height = list(map(int, input().split()))

    height.sort()  # 정렬

    answer = 0
    for i in range(2, n):
        answer = max(answer, abs(height[i] - height[i - 2]))
    print(answer)

# ----

for _ in range(int(input())):
    n = int(input())
    height = list(map(int, input().split()))

    height.sort()  # 정렬

    result = []  # 0, 2, 4, ... i, i - 1, i - 3, ... 1 순서대로 나열
    for i in range(0, n, 2):
        result.append(height[i])
    s = n - 1 if n % 2 == 0 else n - 2
    for i in range(s, 0, -2):
        result.append(height[i])

    answer = 0
    for i in range(0, n - 1):
        answer = max(answer, abs(result[i + 1] - result[i]))
    print(answer)
