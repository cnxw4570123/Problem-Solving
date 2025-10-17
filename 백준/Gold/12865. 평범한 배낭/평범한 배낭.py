import sys


# print = sys.stdout.write
input = sys.stdin.readline


def main():
    init()
    dp = [[0] * (K + 1) for _ in range(N + 1)]

    for i in range(1, N + 1):
        weight, value = products[i - 1]
        dp[i][:weight] = dp[i - 1][:weight]

        for j in range(weight, K + 1):
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight] + value)
    print(dp[N][K])


def init():
    global N, K, products
    N, K = map(int, input().split())
    products = [tuple(map(int, input().split())) for _ in range(N)]


if __name__ == "__main__":
    main()
