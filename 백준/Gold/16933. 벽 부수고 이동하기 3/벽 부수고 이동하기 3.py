import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
#f = open("input.txt", "rt")
#input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
INF = 2_000_000
ROAD, WALL = "0", "1"
DAY, NIGHT = 0, 1
dy, dx = (-1, 0, 1, 0), (0, -1, 0, 1)

N, M, K = map(int, input().split())
_map = [list(input().rstrip()) for _ in range(N)]
dist = [[[0] * M for _ in range(N)] for _ in range(K + 1)]


def BFS():
    dist[0][0][0] = 0

    q = deque([(0, 0, 0, 0, DAY)])
    while q:
        y, x, hammer_left, move, time = q.popleft()

        if (y, x) == (N - 1, M - 1):
            return move + 1

        for dir in range(4):
            ny, nx = y + dy[dir], x + dx[dir]

            if ny >= N or nx >= M or ny < 0 or nx < 0:
                continue

            if _map[ny][nx] == ROAD and not dist[hammer_left][ny][nx]:
                dist[hammer_left][ny][nx] = move + 1
                q.append((ny, nx, hammer_left, move + 1, (time + 1) % 2))
                continue

            if (
                _map[ny][nx] == WALL
                and hammer_left < K
                and not dist[hammer_left + 1][ny][nx]
            ):
                if time == DAY:
                    dist[hammer_left + 1][ny][nx] = dist[hammer_left][ny][nx] + 1
                    q.append((ny, nx, hammer_left + 1, move + 1, (time + 1) % 2))
                    continue
                q.append((y, x, hammer_left, move + 1, (time + 1) % 2))

    return -1


def main():
    print(BFS())


if __name__ == "__main__":
    main()
