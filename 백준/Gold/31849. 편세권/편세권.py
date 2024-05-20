import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
INF = float("inf")
DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)
N, M, R, C = map(int, input().split())
_map = [["."] * (M + 1) for _ in range(N + 1)]


q, cost = deque(), [[INF] * (M + 1) for _ in range(N + 1)]
for _ in range(R):
    a, b, p = map(int, input().split())
    q.append((a, b, p, 0))
    # cost[a][b] = 0

conv = []
for _ in range(C):
    a, b = map(int, input().split())
    conv.append((a, b))
    _map[a][b] = "c"


def BFS():
    while q:
        y, x, c, d = q.popleft()

        for dy, dx in DIRECTIONS:
            ny, nx = dy + y, dx + x

            if ny > N or nx > M or ny < 1 or nx < 1 or cost[ny][nx] <= (d + 1) * c:
                continue
            q.append((ny, nx, c, d + 1))
            cost[ny][nx] = (d + 1) * c


def main():
    BFS()
    print(min([cost[a][b] for a, b in conv]))


if __name__ == "__main__":
    main()
