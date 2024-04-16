import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline
DIRECTIONS = ((-1, 0), (1, 0), (0, -1), (0, 1))
INF = float("inf")
N, M = map(int, input().split())
maps = []
viruses = [()]
virus_num = 1
ans = INF
for i in range(N):
    row = list(map(int, input().split()))
    for j in range(N):
        if row[j] == 2:
            row[j] = virus_num
            viruses.append((i, j))
            virus_num += 1
        else:
            row[j] *= -1
    maps.append(row)
v_combination, v_numbers = set(), [False] * (virus_num + 1)

q = deque()


def main():
    find(0, 1, [])
    print(ans if ans != INF else -1)


def find(cnt, idx, cur):
    global v_combination, ans
    if cnt == M:
        if tuple(cur) in v_combination:
            return
        v_temp = [[INF] * N for _ in range(N)]

        for num in cur:
            vy, vx = viruses[num]
            v_temp[vy][vx] = 0
            q.append((vy, vx, 0))

        BFS(q, v_temp)
        ans = min(ans, find_max(v_temp))
        return

    for i in range(idx, virus_num):
        if v_numbers[i]:
            continue
        v_numbers[i] = True
        cur.append(i)
        find(cnt + 1, i + 1, cur)
        cur.pop()
        v_numbers[i] = False


def BFS(q, v):
    while q:
        y, x, t = q.popleft()

        for dy, dx in DIRECTIONS:
            ny, nx = y + dy, x + dx
            if (
                ny >= N
                or nx >= N
                or ny < 0
                or nx < 0
                or maps[ny][nx] < 0
                or v[ny][nx] <= t + 1
            ):
                continue
            v[ny][nx] = t + 1
            q.append((ny, nx, t + 1))


def find_max(v):
    dist = 0
    for i in range(N):
        for j in range(N):
            if maps[i][j] == 0:
                dist = max(dist, v[i][j])
    return dist


if __name__ == "__main__":
    main()
