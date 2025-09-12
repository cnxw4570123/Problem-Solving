import sys


# print = sys.stdout.write
input = sys.stdin.readline

MAX = sys.maxsize
N = int(input().rstrip())
dp = [MAX for _ in range(10**6 + 1)]
dp[1], dp[2], dp[3] = (0, 1, 1)


def main():
    for i in range(4, N + 1):
        find(i)

    print(dp[N])


def find(num):
    if dp[num] != MAX:
        return dp[num]

    if num % 3 == 0:
        dp[num] = min(dp[num], find(num // 3) + 1)

    if num % 2 == 0:
        dp[num] = min(dp[num], find(num // 2) + 1)

    dp[num] = min(dp[num], find(num - 1) + 1)

    return dp[num]


if __name__ == "__main__":
    main()
