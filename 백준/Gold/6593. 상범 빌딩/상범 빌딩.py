import sys
from collections import deque

input = sys.stdin.readline


def BFS():
    global ans
    v = [[[False for _ in range(C)] for _ in range(R)] for _ in range(L)]
    while q:
        cur = q.popleft()
        if buildings[cur[0]][cur[1]][cur[2]] == "E":
            ans.append(f"Escaped in {cur[3]} minute(s).")
            return
        if v[cur[0]][cur[1]][cur[2]]:
            continue
        v[cur[0]][cur[1]][cur[2]] = True

        for i in range(6):
            nz, ny, nx = cur[0] + dz[i], cur[1] + dy[i], cur[2] + dx[i]
            if (
                nz >= L
                or ny >= R
                or nx >= C
                or nz < 0
                or ny < 0
                or nx < 0
                or v[nz][ny][nx]
                or buildings[nz][ny][nx] == "#"
            ):
                continue
            q.append([nz, ny, nx, cur[3] + 1])
    ans.append("Trapped!")


dz, dy, dx = [-1, 0, 0, 0, 0, 1], [0, -1, 1, 0, 0, 0], [0, 0, 0, -1, 1, 0]
ans = []
while True:
    q = deque()
    L, R, C = map(int, input().rstrip().split())
    if (L, R, C) == (0, 0, 0):
        break
    buildings = []
    for i in range(L):
        temp = []
        for j in range(R):
            col = input().rstrip()
            if col.find("S") != -1:
                q.append([i, j, col.index("S"), 0])
            temp.append(col)
        buildings.append(temp)
        input()
    BFS()
print("\n".join(ans))
