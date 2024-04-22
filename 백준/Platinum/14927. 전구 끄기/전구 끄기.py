import sys
from copy import deepcopy

# print = sys.stdout.write
input = sys.stdin.readline

# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
ON, OFF = 1, 0
dy, dx = (-1, 1, 0, 0, 0), (0, 0, 0, -1, 1)
N = int(input().rstrip())
MAX = N**2 + 1
_map = [list(map(int, input().split())) for _ in range(N)]


def change_status(y, x, board):
    for i in range(5):
        ny, nx = dy[i] + y, dx[i] + x
        if ny >= N or nx >= N or ny < 0 or nx < 0:
            continue
        board[ny][nx] ^= 1


def main():
    ans = MAX
    # 2^18 = 262144가지의 경우의 수
    for tries in range(1 << N):
        board = deepcopy(_map)
        cnt = 0
        for col in range(N):
            if tries & (1 << col):
                change_status(0, col, board)
                cnt += 1

        for row in range(1, N):
            for col in range(N):
                if board[row - 1][col] == ON:
                    change_status(row, col, board)
                    cnt += 1

        if board[-1].count(0) == N:
            ans = min(ans, cnt)

    print(ans if ans != MAX else -1)


if __name__ == "__main__":
    main()
