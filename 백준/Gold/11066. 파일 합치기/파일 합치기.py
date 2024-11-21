import sys

# print = sys.stdout.write
input = sys.stdin.readline

INF = float("inf")

T = int(input().rstrip())


def main():
    for _ in range(T):
        K = int(input().rstrip())

        dp = [[INF] * (K + 1) for _ in range(K + 1)]
        weight = [0] + list(map(int, input().split()))

        for i in range(1, K + 1):
            dp[i][i] = 0
            weight[i] += weight[i - 1]

        for i in range(K - 1, 0, -1):
            for j in range(i + 1, K + 1):
                # 합치는 비용은 총 용량과 같음
                merging_cost = weight[j] - weight[i - 1]

                gap = j - i
                dp[i][j] = (
                    min([dp[i][i + k] + dp[i + k + 1][j] for k in range(gap)])
                    + merging_cost
                )

        print(dp[1][K])


if __name__ == "__main__":
    main()
