import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
#f = open("input.txt", "rt")
#input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

INF = float("inf")
DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)
WALL, ROAD = 1, 0

N, M, K = map(int, input().split())

_map = [list(map(int, input().rstrip())) for _ in range(N)]

v = [[[0] * M for _ in range(N)] for _ in range(K + 1)]


def dijkstra():
    q = deque([(0, 0, K)])

    while q:
        y, x, cnt = q.popleft()

        if (y, x) == (N - 1, M - 1):
            return v[cnt][y][x] + 1

        for dy, dx in DIRECTIONS:
            ny, nx = dy + y, dx + x
            if ny >= N or nx >= M or ny < 0 or nx < 0:
                continue

            if _map[ny][nx] == ROAD and not v[cnt][ny][nx]:
                q.append((ny, nx, cnt))
                v[cnt][ny][nx] = v[cnt][y][x] + 1
                continue

            if _map[ny][nx] == WALL and cnt > 0 and not v[cnt - 1][ny][nx]:
                q.append((ny, nx, cnt - 1))
                v[cnt - 1][ny][nx] = v[cnt][y][x] + 1
    return -1


def main():
    print(dijkstra())


if __name__ == "__main__":
    main()
