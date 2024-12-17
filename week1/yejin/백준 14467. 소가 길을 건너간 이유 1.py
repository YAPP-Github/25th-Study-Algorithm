answer = 0
count = dict()

for _ in range(int(input())):
    n, pos = map(int, input().split())
    if n not in count:
        count[n] = pos

    if count[n] != pos:
        count[n] = pos
        answer += 1

print(answer)
