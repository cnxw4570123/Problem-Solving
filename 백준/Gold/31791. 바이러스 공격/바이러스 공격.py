import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)

N, M = map(int, input().split())
T, durability, X, B = map(int, input().split())

visited = [[False] * (M + 1) for _ in range(N + 1)]

_map = [[]]
virus_q = deque()
for i in range(1, N + 1):
    row = ["x"] + list(input().rstrip())
    for j in range(1, M + 1):
        if row[j] == "*":
            virus_q.append((i, j))
            visited[i][j] = True
    _map.append(row)
v_building = set()
building_q = deque()


def propagate(time):
    while building_q:
        if time - building_q[0][0] < durability:
            break
        t, y, x = building_q.popleft()
        visited[y][x] = True
        virus_q.append((y, x))


def BFS():
    time = 0
    while (virus_q or building_q) and time < T:
        time += 1
        q_size = len(virus_q)
        for _ in range(q_size):
            y, x = virus_q.popleft()

            for dy, dx in DIRECTIONS:
                ny, nx = dy + y, dx + x

                if (
                    ny > N
                    or nx > M
                    or ny < 1
                    or nx < 1
                    or visited[ny][nx]
                    or (ny, nx) in v_building
                ):
                    continue

                if _map[ny][nx] == "#":
                    v_building.add((ny, nx))
                    building_q.append((time, ny, nx))
                elif _map[ny][nx] == ".":
                    virus_q.append((ny, nx))
                    visited[ny][nx] = True
        propagate(time)


def main():
    BFS()
    ans = []
    for i in range(1, N + 1):
        for j in range(1, M + 1):
            if visited[i][j]:
                continue
            ans.append(f"{i} {j}")

    if not ans:
        print("-1")
        return
    print("\n".join(ans))


if __name__ == "__main__":
    main()
