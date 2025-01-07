import sys
input = sys.stdin.readline

n = int(input())
names = [""] + [input().rstrip() for _ in range(n)]
next = [-1] * (n + 1)
tail = [i for i in range(n + 1)]  # 최초의 모든 i의 꼬리를 자기 자신으로 연결

for _ in range(n - 1):
    i, j = map(int, input().split())
    next[tail[i]] = j  # i의 마지막에 j 연결
    tail[i] = tail[j]  # i의 마지막으로 j의 마지막 연결

for _ in range(n):
    print(names[i], end="")
    i = next[i]