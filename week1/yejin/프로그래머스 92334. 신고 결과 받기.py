from collections import defaultdict

def solution(id_list, report, k):
    report = list(set(report))  # 유저의 신고 중복 제거
    rep, count = defaultdict(list), defaultdict(int)  # 유저별 신고한 ID, 유저별 신고당한 횟수

    for r in report:
        a, b = r.split()
        rep[a].append(b)  # a가 b 신고
        count[b] += 1

    answer = []
    for i in id_list:
        result = 0
        for r in rep[i]:
            if count[r] >= k:  # k번 이상 신고된 유저
                result += 1
        answer.append(result)

    return answer
