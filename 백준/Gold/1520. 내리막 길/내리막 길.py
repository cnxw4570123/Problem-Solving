import sys

sys.setrecursionlimit(10**8)
# print = sys.stdout.write
input = sys.stdin.readline


N, M = map(int, input().split())
DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)
board = [list(map(int, input().split())) for _ in range(N)]
dp = [[-1] * M for _ in range(N)]


def main():
    print(DFS(0, 0))


def DFS(y, x):
    if (y, x) == (N - 1, M - 1):
        return 1  # 목적지에 도착했다면 +1

    if dp[y][x] != -1:
        return dp[y][x]  # 이미 탐색한 경로면 가져다씀.

    # 한 번도 계산 안되었으므로 일단 방문처리
    dp[y][x] = 0

    for dy, dx in DIRECTIONS:
        ny, nx = dy + y, dx + x
        if ny >= N or nx >= M or ny < 0 or nx < 0 or board[ny][nx] >= board[y][x]:
            continue
        # (ny, nx)에서 목적지까지 s가는 경우의 수 더하기
        dp[y][x] += DFS(ny, nx)

    return dp[y][x]


if __name__ == "__main__":
    main()
