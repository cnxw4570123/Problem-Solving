import sys
from collections import deque

# print = sys.stdout.write
# f = open("input.txt", "rt")
# input = f.readline
input = sys.stdin.readline
hy, hx = (-2, -2, -1, 1, 2, 2, 1, -1), (-1, 1, 2, 2, 1, -1, -2, -2)
dy, dx = (-1, 1, 0, 0), (0, 0, -1, 1)

K = int(input().rstrip())
W, H = map(int, input().rstrip().split())
maps = [list(map(int, input().rstrip().split())) for _ in range(H)]


def main():
    v = [[[float("inf")] * W for _ in range(H)] for _ in range(K + 1)]
    BFS(v)
    ans = float("inf")
    for i in range(K + 1):
        ans = min(ans, v[i][H - 1][W - 1])
    print(-1 if ans == float("inf") else ans)


def BFS(v):
    q = deque([])
    q.append((0, 0, 0, K))
    v[K][0][0] = 0
    while q:
        y, x, cnt, horse_move = q.popleft()

        if horse_move > 0:
            for i in range(8):
                ny, nx = y + hy[i], x + hx[i]
                if (
                    ny >= H
                    or nx >= W
                    or ny < 0
                    or nx < 0
                    or v[horse_move - 1][ny][nx] <= cnt + 1
                    or maps[ny][nx] == 1
                ):
                    continue
                q.append((ny, nx, cnt + 1, horse_move - 1))
                v[horse_move - 1][ny][nx] = cnt + 1
        for i in range(4):
            ny, nx = y + dy[i], x + dx[i]
            if (
                ny >= H
                or nx >= W
                or ny < 0
                or nx < 0
                or v[horse_move][ny][nx] <= cnt + 1
                or maps[ny][nx] == 1
            ):
                continue
            q.append((ny, nx, cnt + 1, horse_move))
            v[horse_move][ny][nx] = cnt + 1


if __name__ == "__main__":
    main()
