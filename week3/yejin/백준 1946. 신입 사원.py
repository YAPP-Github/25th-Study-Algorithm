import sys
input = sys.stdin.readline

for _ in range(int(input())):
    n = int(input())
    scores = [list(map(int, input().split())) for _ in range(n)]
    scores.sort()  # 서류심사 성적 낮은 순서대로, 면접 성적의 순위 낮은 순서대로 정렬

    top = 0  # 앞선 지원자들 중 가장 점수가 높은 지원자
    result = 1  # 첫번째 사람 포함
    for i in range(1, n):
        if scores[i][1] < scores[top][1]:  # 면접 성적의 순위 비교
            top = i
            result += 1

    print(result)