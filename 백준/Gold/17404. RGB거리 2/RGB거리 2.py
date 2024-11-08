import sys


# print = sys.stdout.write
input = sys.stdin.readline


RED, GREEN, BLUE = 0, 1, 2
INF = sys.maxsize

N = int(input().rstrip())
costs = [list(map(int, input().split())) for _ in range(N)]

dp = [[0] * 3 for _ in range(N + 1)]


def main():
    ans = INF
    for i in range(3):
        dp[1] = [INF if i != j else costs[0][j] for j in range(3)]
        for j in range(2, N + 1):
            dp[j][RED] = min(dp[j - 1][GREEN], dp[j - 1][BLUE]) + costs[j - 1][RED]
            dp[j][GREEN] = min(dp[j - 1][RED], dp[j - 1][BLUE]) + costs[j - 1][GREEN]
            dp[j][BLUE] = min(dp[j - 1][RED], dp[j - 1][GREEN]) + costs[j - 1][BLUE]

        ans = min(ans, min([dp[N][j] for j in range(3) if i != j]))
    print(ans)


if __name__ == "__main__":
    main()
