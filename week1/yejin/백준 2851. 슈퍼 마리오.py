scores = [int(input()) for _ in range(10)]
total = 0

for s in scores:
    total += s
    if total >= 100:
        if total - 100 > 100 - (total - s):
            total -= s
            break

print(total)
