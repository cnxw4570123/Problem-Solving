import sys

# print = sys.stdout.write
input = sys.stdin.readline

N, M = map(int, input().split())
volunteer_points = [0] + list(map(int, input().split()))
A, D = map(int, input().split())

volunteer_points += [0] * (D - 1)
dp = [[0] * (N + D) for _ in range((N + D - 1) // D + 1)]


def main():
    # init
    for i in range(1, N + D):
        dp[0][i] = dp[0][i - 1] + volunteer_points[i]

    for i in range(1, len(dp)):
        for j in range(D, N + D):
            dp[i][j] = max(dp[i - 1][j - D] + A, dp[i][j - 1] + volunteer_points[j])

    for i in range(len(dp)):
        if dp[i][-1] >= M:
            print(i)
            return

    print(-1)


if __name__ == "__main__":
    main()
