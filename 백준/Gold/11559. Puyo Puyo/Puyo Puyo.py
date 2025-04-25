import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline


DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)
row_max, COL_MAX, EMPTY = 12, 6, "."

_map = []
for _ in range(row_max):
    line = input().rstrip()
    if line == EMPTY * 6:
        continue
    _map.append(list(line))

row_max = len(_map)

q = deque()


def main():
    boom = 0

    while True:
        # print("\n".join(map(str, _map)))
        v = [[False] * COL_MAX for _ in range(row_max)]
        for i in range(row_max):
            for j in range(COL_MAX):
                if v[i][j] or _map[i][j] == EMPTY:
                    continue
                v[i][j] = True
                cnt = find(i, j, v, _map[i][j]) + 1
                if cnt > 3:
                    q.append((i, j))

        if not q:
            break
        v = [[False] * COL_MAX for _ in range(row_max)]
        while q:
            y, x = q.popleft()
            v[y][x] = True
            delete(y, x, v, _map[y][x])
            _map[y][x] = EMPTY

        cleanup()
        boom += 1
        # print("\n")

    print(boom)


def find(y, x, v, criteria):
    current = 0
    for dy, dx in DIRECTIONS:
        ny, nx = y + dy, x + dx
        if is_not_reachable(ny, nx, v, criteria):
            continue
        v[ny][nx] = True
        current += find(ny, nx, v, criteria) + 1

    return current


def delete(y, x, v, criteria):
    for dy, dx in DIRECTIONS:
        ny, nx = dy + y, dx + x
        if is_not_reachable(ny, nx, v, criteria):
            continue
        v[ny][nx] = True
        _map[ny][nx] = EMPTY
        delete(ny, nx, v, criteria)


def cleanup():
    for _ in range(row_max):
        for i in range(row_max - 1, 0, -1):
            for j in range(COL_MAX):
                if _map[i][j] != EMPTY:
                    continue
                _map[i][j] = _map[i - 1][j]
                _map[i - 1][j] = EMPTY


def is_not_reachable(ny, nx, v, criteria):
    return (
        ny >= row_max
        or nx >= COL_MAX
        or ny < 0
        or nx < 0
        or v[ny][nx]
        or _map[ny][nx] != criteria
    )


if __name__ == "__main__":
    main()
