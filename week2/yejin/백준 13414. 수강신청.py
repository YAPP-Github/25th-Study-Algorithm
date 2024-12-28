import sys
input = sys.stdin.readline

k, l = map(int, input().split())
dict = dict()

for i in range(l):
    dict[input().rstrip()] = i
result = sorted(dict.items(), key=lambda x: x[1])  # 순서 기준으로 정렬

if k > len(result):  # 수강 가능 인원보다 신청 인원이 적은 경우
    k = len(result)

for i in range(k):
    print(result[i][0])
