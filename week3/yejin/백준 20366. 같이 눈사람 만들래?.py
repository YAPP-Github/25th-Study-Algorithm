n = int(input())
h = list(map(int, input().split()))
h.sort()

result = 1e9
for i in range(n - 3):
    for j in range(i + 3, n):  # i와 j의 차이는 3 이상이어야 한다. i와 j 사이에 최소 원소 2개 이상 존재해야 하기 때문
        elsa = h[i] + h[j]
        left, right = i + 1, j - 1

        while left < right:  # two pointer
            anna = h[left] + h[right]
            if abs(elsa - anna) < result:  # 최소 차이 갱신
                result = abs(elsa - anna)
            if result == 0:  # 차이가 0이면 가장 최소인 경우이므로 종료 (시간 단축)
                print(0)
                exit()

            if anna < elsa:
                left += 1
            else:
                right -= 1

print(result)
