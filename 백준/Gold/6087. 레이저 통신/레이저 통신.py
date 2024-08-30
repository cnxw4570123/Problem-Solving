import sys
from heapq import heappop, heappush

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
#f = open("input.txt", "rt")
#input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
INF = float("inf")
WALL = "*"
dy, dx = (-1, 0, 1, 0), (0, 1, 0, -1)
W, H = map(int, input().split())

_map = []
s = []
for i in range(H):
    row = list(input().rstrip())
    for j in range(W):
        if row[j] == "C":
            s.append((i, j))
    _map.append(row)


min_reflection = [[[INF] * 4 for _ in range(W)] for _ in range(H)]


def is_cross(dir, new_dir):
    return dir != new_dir and (dir + 2) % 4 != new_dir


def is_not_in_range(y, x):
    return y >= H or x >= W or y < 0 or x < 0 or _map[y][x] == WALL


def dijkstra(y, x):
    min_reflection[y][x] = [0] * 4
    hq = [
        (0, i, y + dy[i], x + dx[i])
        for i in range(4)
        if not is_not_in_range(y + dy[i], x + dx[i])
    ]

    for _, i, y, x in hq:
        min_reflection[y][x][i] = 0

    while hq:
        cnt, dir, y, x = heappop(hq)

        for i in range(4):
            ny, nx = y + dy[i], x + dx[i]
            ncnt = cnt
            if is_cross(dir, i):
                ncnt += 1

            if is_not_in_range(ny, nx) or ncnt >= min_reflection[ny][nx][i]:
                continue
            min_reflection[ny][nx][i] = ncnt
            heappush(hq, (ncnt, i, ny, nx))


def main():
    dijkstra(*s[0])
    ey, ex = s[1]
    print(min(min_reflection[ey][ex]))


if __name__ == "__main__":
    main()
