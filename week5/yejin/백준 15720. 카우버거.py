b, c, d = map(int, input().split())
burgers = sorted(list(map(int, input().split())))
sides = sorted(list(map(int, input().split())))
drinks = sorted(list(map(int, input().split())))

total = sum(burgers) + sum(sides) + sum(drinks)  # 세트 할인이 적용되기 전 가격
print(total)

for _ in range(min(b, c, d)):
    result = burgers.pop() + sides.pop() + drinks.pop()  # 가격이 높은 메뉴부터 세트 구성 -> 가장 최소 가격
    total -= int(result * 0.1)  # 10%의 세트 할인을 적용

print(total)