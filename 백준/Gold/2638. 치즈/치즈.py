import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
v = [[False] * M for _ in range(N)]
s = [(0, 0)]


def main():
    t = 0
    while s:
        t += 1
        DFS()
        for ny, nx in s:
            board[ny][nx] = 0
            v[ny][nx] = True
    print(t - 1)


def DFS():
    v[0][0] = True

    cheese_candidate = set()

    while s:
        y, x = s.pop()

        for dy, dx in DIRECTIONS:
            ny, nx = dy + y, dx + x
            if ny >= N or nx >= M or ny < 0 or nx < 0 or v[ny][nx]:
                continue

            if board[ny][nx] == 0:
                s.append((ny, nx))
                v[ny][nx] = True
                continue

            cheese_candidate.add((ny, nx))

    # 여기서 스택에 삽입
    for cy, cx in cheese_candidate:
        cnt = 0
        for dy, dx in DIRECTIONS:
            ny, nx = cy + dy, cx + dx

            if ny >= N or nx >= M or ny < 0 or nx < 0 or not v[ny][nx]:
                continue

            cnt += 1

        if cnt >= 2:
            s.append((cy, cx))


if __name__ == "__main__":
    main()
