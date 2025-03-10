import sys
import math

# print = sys.stdout.write
input = sys.stdin.readline

sys.setrecursionlimit(10**6)
N = int(input().rstrip())
INF = sys.maxsize
dp = [INF] * (N + 1)


def main():
    for i in range(1, int(math.sqrt(N)) + 1):
        dp[i**2] = 1

    print(find(N))


def find(n):
    if n == 0:
        return 0

    if dp[n] != INF:
        return dp[n]

    for i in range(int(math.sqrt(n)), 0, -1):
        dp[n] = min(dp[n], find(n - i**2) + dp[i**2])

    return dp[n]


if __name__ == "__main__":
    main()
