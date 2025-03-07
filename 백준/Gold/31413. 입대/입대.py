import sys

sys.setrecursionlimit(10**8)
# print = sys.stdout.write
input = sys.stdin.readline

INF = sys.maxsize
N, M = map(int, input().split())
score = [0] + list(map(int, input().split()))
A, D = map(int, input().split())
dp = [[0] * (N + D + 1) for _ in range(N // D + 2)]


def main():
    global score
    score += [0] * D
    for i in range(1, N + 1):
        dp[0][i] = dp[0][i - 1] + score[i]

    ans = INF
    for count in range(N // D + 2):
        for day in range(D, N + D):
            if count > 0:
                dp[count][day] = max(
                    dp[count - 1][day - D] + A, dp[count][day - 1] + score[day]
                )

            if dp[count][day] >= M:
                ans = min(ans, count)
    print(ans if ans != INF else -1)


if __name__ == "__main__":
    main()
