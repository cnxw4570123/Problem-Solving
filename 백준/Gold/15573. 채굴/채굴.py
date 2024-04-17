import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

# 반드시 아래 두 라인 주석처리 후 업로드
# f = open("input.txt", "rt")
# input = f.readline
# 위 두라인 주석처리 후 업로드

DIRECTIONS = ((-1, 0), (1, 0), (0, -1), (0, 1))

N, M, K = map(int, input().split())
maps = [list(map(int, input().split())) for _ in range(N)]


def main():
    start, end = 1, 10**6

    while start < end:
        d = (start + end) >> 1

        if BFS(d) < K:
            start = d + 1
        else:
            end = d

    print(end)


def prepare_mining(d, v):
    q = deque()
    for i in range(N):
        for j in (0, M - 1):
            if maps[i][j] <= d:
                q.append((i, j))
                v[i][j] = True

    for i in range(1, M - 1):
        if maps[0][i] <= d:
            q.append((0, i))
            v[0][i] = True
    return q


def BFS(d):
    v = [[False] * M for _ in range(N)]
    q = prepare_mining(d, v)
    cnt = len(q)

    while q:
        y, x = q.popleft()

        for dy, dx in DIRECTIONS:
            ny, nx = dy + y, dx + x
            if ny >= N or nx >= M or ny < 0 or nx < 0 or v[ny][nx] or maps[ny][nx] > d:
                continue
            q.append((ny, nx))
            v[ny][nx] = True
            cnt += 1

    return cnt


if __name__ == "__main__":
    main()
