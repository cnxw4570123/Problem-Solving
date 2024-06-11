import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

N, M = map(int, input().split())
ans = ["No", "Yes"]
DIRECTIONS = (1, 0), (0, 1)

_map = [list(map(int, input().split())) for _ in range(M)]
v = [[False] * N for _ in range(M)]


def can_not_go(y, x):
    return y >= M or x >= N or y < 0 or x < 0 or v[y][x] or not _map[y][x]


def BFS():
    v[0][0] = True
    q = deque([(0, 0)])

    while q:
        y, x = q.popleft()

        if y == M - 1 and x == N - 1:
            return 1

        for dy, dx in DIRECTIONS:
            ny, nx = y + dy, x + dx
            if can_not_go(ny, nx):
                continue
            v[ny][nx] = True
            q.append((ny, nx))
    return 0


def main():
    print(ans[BFS()])


if __name__ == "__main__":
    main()
