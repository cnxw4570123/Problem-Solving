import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

DIRECTIONS = (-1, 0, 0), (1, 0, 0), (0, -1, 0), (0, 1, 0), (0, 0, -1), (0, 0, 1)


def main():
    ans = []
    while init():
        ans.append(BFS())
    print("\n".join(ans))


def init():
    global L, R, C, board, q, v
    L, R, C = map(int, input().split())
    if (L, R, C) == (0, 0, 0):
        return False

    board, q = [], deque()
    v = [[[False] * C for _ in range(R)] for _ in range(L)]

    for i in range(L):
        floor = []
        for j in range(R):
            row = list(input().rstrip())
            for k in range(C):
                if row[k] == "S":
                    q.append((i, j, k, 0))
                    v[i][j][k] = True
            floor.append(row)
        board.append(floor)
        input()

    return True


def BFS():

    while q:
        z, y, x, t = q.popleft()

        if board[z][y][x] == "E":
            return f"Escaped in {t} minute(s)."

        for dz, dy, dx in DIRECTIONS:
            nz, ny, nx = dz + z, dy + y, dx + x
            if nz >= L or ny >= R or nx >= C or nz < 0 or ny < 0 or nx < 0:
                continue
            if v[nz][ny][nx] or board[nz][ny][nx] == "#":
                continue
            q.append((nz, ny, nx, t + 1))
            v[nz][ny][nx] = True

    return "Trapped!"


if __name__ == "__main__":
    main()
