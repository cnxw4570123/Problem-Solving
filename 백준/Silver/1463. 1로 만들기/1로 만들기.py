# prob : 1463
# https://www.acmicpc.net/problem/1463


def make_number(memo, i):
    if memo[i]:
        return memo[i]
    else:
        i_minus_one = 1 + make_number(memo, i - 1)
        div_3, div_2 = int(1e9), int(1e9)
        if i % 3 == 0:
            div_3 = memo[3] + make_number(memo, i // 3)

        if i % 2 == 0:
            div_2 = memo[2] + make_number(memo, i // 2)

        memo[i] = min(div_3, div_2, i_minus_one)

    return memo[i]


ans = 0
n = int(input())
memo = [0, 0, 1, 1] + [0 for _ in range(4, n + 1)]  # 메모이제이션 준비


for i in range(4, n + 1):  # 0 ~ n
    make_number(memo, i)


print(memo[n])
