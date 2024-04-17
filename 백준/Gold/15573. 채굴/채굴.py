import sys
from collections import deque

n, m, k = map(int, sys.stdin.readline().split())

stone = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]


def bfs(d):
    queue = deque()
    visited = [[False] * m for _ in range(n)]
    dx, dy = [-1, 0, 1, 0], [0, -1, 0, 1]
    cnt = 0

    for i in range(m):
        visited[0][i] = True
        if stone[0][i] <= d:
            queue.append((0, i))
            cnt += 1
    for i in range(1, n):
        visited[i][0] = True
        if stone[i][0] <= d:
            queue.append((i, 0))
            cnt += 1
        visited[i][m-1] = True
        if stone[i][m-1] <= d:
            queue.append((i, m-1))
            cnt += 1

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if visited[nx][ny]:
                continue

            visited[nx][ny] = True
            if stone[nx][ny] <= d:
                queue.append((nx, ny))
                cnt += 1

    return cnt


start, end = 1, 10**6
min_d = end
while start < end:
    mid = (start + end) // 2
    my_k = bfs(mid)

    if my_k >= k:
        min_d = min(min_d, mid)
        end = mid
    else:
        start = mid + 1

sys.stdout.write(str(min_d))