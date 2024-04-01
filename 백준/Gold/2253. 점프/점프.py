import sys

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline

# N(N + 1) = 20_000
# 141 * 142 = 20_022
MAX_STEP, INF = 141, float("inf")
N, M = map(int, input().split())
small_stones = set()  # 밟을 수 없는 칸

# dp[a][b] = c, a칸을 점프해서 b로 가는 방법은 c개이다
dp = [[INF] * (N + 1) for _ in range(MAX_STEP + 1)]


def main():
    for _ in range(M):
        small_stones.add(int(input().rstrip()))
    # while row := input().split():
    #     small_stones.update(map(int, row))
    if 2 not in small_stones:
        dp[1][2] = 1  # 1번째 돌에서 1칸을 움직인 상태로 2번째 돌로 이동

    for i in range(2, N + 1):
        for j in range(1, MAX_STEP + 1):
            if not can_step_on(i) or not dp[j][i]:
                continue
            # j - 1칸 움직일 수 있으면
            if can_move(i, j - 1):
                dp[j - 1][i + j - 1] = min(dp[j][i] + 1, dp[j - 1][i + j - 1])
            # j칸을 움직일 수 있으면
            if can_move(i, j):
                dp[j][i + j] = min(dp[j][i] + 1, dp[j][i + j])
            # j + 1칸을 움직일 수 있으면
            if can_move(i, j + 1):
                dp[j + 1][i + j + 1] = min(dp[j][i] + 1, dp[j + 1][i + j + 1])

    ans = INF
    for i in range(1, MAX_STEP + 1):
        ans = min(ans, dp[i][N])

    print(ans if ans != INF else -1)


def can_move(i, step):
    # 최소 한 칸 이상 움직일 수 있고 step만큼 움직였을 때 최대 N칸 이하일 경우만
    return 0 < step <= MAX_STEP and i + step <= N and can_step_on(i + step)


def can_step_on(idx):
    return idx not in small_stones


if __name__ == "__main__":
    main()
