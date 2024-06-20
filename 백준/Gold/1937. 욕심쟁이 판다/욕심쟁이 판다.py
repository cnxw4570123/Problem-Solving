import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출5
DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)

N = int(input().rstrip())
_map = [list(map(int, input().split())) for _ in range(N)]
dp = [[0] * N for _ in range(N)]


def DFS(y, x, v):
    global dp
    if not dp[y][x]:
        dp[y][x] = 1
    v.add((y, x))
    food = _map[y][x]
    for dy, dx in DIRECTIONS:
        ny, nx = dy + y, dx + x
        if (
            ny >= N
            or nx >= N
            or ny < 0
            or nx < 0
            or _map[ny][nx] <= food
            # or (ny, nx) in v
        ):
            continue

        dp[y][x] = max(dp[y][x], (dp[ny][nx] if dp[ny][nx] else DFS(ny, nx, v)) + 1)

    return dp[y][x]


def main():
    for i in range(N):
        for j in range(N):
            if dp[i][j]:
                continue
            DFS(i, j, set())

    print(max([max(dp[i]) for i in range(N)]))


if __name__ == "__main__":
    main()
