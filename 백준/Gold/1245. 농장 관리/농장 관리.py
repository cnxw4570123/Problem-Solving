import sys
from collections import deque
from heapq import heappush, heappop

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
#f = open("input.txt", "rt")
#input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
DIRECTIONS = ((-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1))

heapq = []
N, M = map(int, input().split())
_map = []
for i in range(N):
    row = list(map(int, input().split()))
    for j in range(M):
        if not row[j]:
            continue
        heappush(heapq, (-row[j], i, j))
    _map.append(row)

v = [[False] * M for _ in range(N)]


def BFS(r, c, val):
    q = deque([(r, c, val)])
    v[r][c] = True
    while q:
        y, x, value = q.popleft()

        for dy, dx in DIRECTIONS:
            ny, nx = dy + y, dx + x
            if (
                ny >= N
                or nx >= M
                or ny < 0
                or nx < 0
                or v[ny][nx]
                or not _map[ny][nx]
                or _map[ny][nx] > value
            ):
                continue

            q.append((ny, nx, _map[ny][nx]))
            v[ny][nx] = True


def main():
    ans = 0
    while heapq:
        val, r, c = heappop(heapq)
        if v[r][c]:
            continue
        BFS(r, c, -val)
        ans += 1
    print(ans)


if __name__ == "__main__":
    main()
