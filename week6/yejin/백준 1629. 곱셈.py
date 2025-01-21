a, b, c = map(int, input().split())
result = a

def multiply(a, b, c):
    if b == 1:
        return a % c

    if b % 2 == 0:
        return (multiply(a, b // 2, c) ** 2) % c  # 2^32 = 2^16 * 2^16 (Divide and Conquer)
    else:
        return ((multiply(a, b // 2, c) ** 2) * a) % c

print(multiply(a, b, c))