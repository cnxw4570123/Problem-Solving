import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
INF = float("inf")
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

N = int(input().rstrip())
board = [list(input().rstrip()) for _ in range(N)]
v = [[INF] * N for _ in range(N)]


def BFS():
    q = deque()
    q.append((0, 0, 0))  # 좌표 + 벽 부순 횟수
    v[0][0] = 0
    while q:
        y, x, cnt = q.popleft()
        if (y, x) == (N - 1, N - 1):
            continue

        for dy, dx in (-1, 0), (1, 0), (0, -1), (0, 1):
            ny, nx = dy + y, dx + x
            if ny >= N or nx >= N or ny < 0 or nx < 0 or v[ny][nx] <= cnt:
                continue
            if board[ny][nx] == "1":
                q.append((ny, nx, cnt))
                v[ny][nx] = cnt
                continue

            if board[ny][nx] == "0" and v[ny][nx] > cnt + 1:
                q.append((ny, nx, cnt + 1))
                v[ny][nx] = cnt + 1


def main():
    BFS()
    print(v[N - 1][N - 1])


if __name__ == "__main__":
    main()
