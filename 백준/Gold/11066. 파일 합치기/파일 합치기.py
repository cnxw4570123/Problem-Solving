import sys

# print = sys.stdout.write
input = sys.stdin.readline


INF = float("inf")

T = int(input().rstrip())


def main():
    for _ in range(T):
        K = int(input().rstrip())

        dp = [[INF] * (K + 1) for _ in range(K + 1)]
        weight = [0] * (K + 1)

        sum = 0
        for i, w in zip(range(1, K + 1), map(int, input().split())):
            dp[i][i] = 0
            sum += w
            weight[i] = sum

        for i in range(K - 1, 0, -1):
            for j in range(i + 1, K + 1):
                # 몇개의 구간
                for k in range(j - i):
                    dp[i][j] = min(
                        dp[i][j],
                        dp[i][i + k] + dp[i + k + 1][j] + weight[j] - weight[i - 1],
                    )
        print(dp[1][K])


if __name__ == "__main__":
    main()
