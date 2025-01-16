for _ in range(int(input())):
    n = int(input())
    prices = list(map(int, input().split()))

    result, max_price = 0, 0  # 이익, 주식의 최대값

    for i in range(len(prices) - 1, -1, -1):  # 뒤에서부터 탐색(미래의 주식부터)
        if prices[i] > max_price:
            max_price = prices[i]  # 주식의 최대 가격 갱신
        else:
            result += max_price - prices[i]  # 현재 가격이 최대 가격보다 작다면 차익 더하기

    print(result)