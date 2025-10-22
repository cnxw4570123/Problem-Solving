import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)


def main():
    global v, q
    init()

    while q:
        y, x = q.popleft()

        for dy, dx in DIRECTIONS:
            ny, nx = dy + y, dx + x
            if can_not_go(ny, nx):
                continue
            if _map[ny][nx] == ".":
                v[ny][nx] = True
                q.append((ny, nx))
                continue

            if _map[ny][nx] == "+":
                ny, nx = move_in_ice(y, x, dy, dx)
                if v[ny][nx]:
                    continue
                v[ny][nx] = True
                q.append((ny, nx))

    ans = []
    for i in range(N):
        row = []
        for j in range(M):
            if _map[i][j] == "#":
                row.append("#")
                continue

            if not v[i][j] and _map[i][j] == ".":
                row.append("P")
                continue

            row.append(_map[i][j])
        ans.append("".join(map(str, row)))

    print("\n".join(map(str, ans)))


def move_in_ice(y, x, dy, dx):
    global v
    ny, nx = dy + y, dx + x
    while _map[ny][nx] == "+":
        ny, nx = dy + ny, dx + nx

    if _map[ny][nx] == "#":
        return (ny - dy, nx - dx)

    return (ny, nx)


def can_not_go(y, x):
    return y >= N or x >= M or y < 0 or x < 0 or v[y][x] or _map[y][x] == "#"


def init():
    global N, M, _map, q, v
    q = deque()
    N, M = map(int, input().split())
    _map = []
    v = [[False] * M for _ in range(N)]
    for i in range(N):
        row = list(input().rstrip())
        for j in range(M):
            if row[j] == "W":
                q.append((i, j))
                v[i][j] = True
        _map.append(row)


if __name__ == "__main__":
    main()
