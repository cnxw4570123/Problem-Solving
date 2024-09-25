import sys
from collections import deque

# print = sys.stdout.write

DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)

N = int(input().rstrip())
_map = []
_min, _max = 101, 0
for _ in range(N):
    row = list(map(int, input().split()))
    for i in range(N):
        if row[i] < _min:
            _min = row[i]
        if row[i] > _max:
            _max = row[i]

    _map.append(row)


def BFS(y, x, v):
    q = deque([(y, x)])
    while q:
        y, x = q.popleft()

        for dy, dx in DIRECTIONS:
            ny, nx = dy + y, dx + x
            if ny >= N or nx >= N or ny < 0 or nx < 0 or v[ny][nx]:
                continue
            q.append((ny, nx))
            v[ny][nx] = True


def main():
    ans = 0
    for depth in range(_min - 1, _max + 1):
        temp = 0
        v = [[_map[i][j] <= depth for j in range(N)] for i in range(N)]
        for i in range(N):
            for j in range(N):
                if v[i][j]:
                    continue
                v[i][j] = True
                temp += 1
                BFS(i, j, v)
        ans = max(ans, temp)
    print(ans)


if __name__ == "__main__":
    main()
