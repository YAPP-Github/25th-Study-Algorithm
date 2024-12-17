n, b = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(n)]

def multiply(arr1, arr2):
    result = [[0] * n for _ in range(n)]

    for i in range(n):
        for j in range(n):
            for k in range(n):
                result[i][j] += arr1[i][k] * arr2[k][j]
            result[i][j] %= 1000

    return result

def square(matrix, b):  # 분할 정복
    if b == 1:
        return matrix

    if b % 2 == 0:  # 짝수일 때
        half = square(matrix, b // 2)  # 절반으로 분할
        return multiply(half, half)  # 절반으로 분할한 뒤 거듭제곱
    else:
        return multiply(matrix, square(matrix, b - 1))  # n - 1(짝수)번 곱한 값에 matrix 곱하기

for s in square(matrix, b):
    print(*[r % 1000 for r in s])
