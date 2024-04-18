import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

# 반드시 아래 두 라인 주석처리 후 업로드
# f = open("input.txt", "rt")
# input = f.readline
# 위 두라인 주석처리 후 업로드
INF = float("inf")
DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)

dust_cnt = 0


def init(W, H, _map, vacuum_and_dusts):
    d_num = 1
    for i in range(H):
        row = list(input().rstrip())
        for j in range(W):
            if row[j] == "o":
                vacuum_and_dusts.append((i, j, 0))
                row[j] = "0"
                continue
            if row[j] == "*":
                vacuum_and_dusts.append((i, j, d_num))
                row[j] = str(d_num)
                d_num += 1
        _map.append(row)


def BFS(H, W, _map, dists, start):
    v = [[False] * W for _ in range(H)]

    v[start[0]][start[1]] = True
    q = deque([(start[0], start[1])])

    t = 0
    while q:
        t += 1
        q_size = len(q)
        for _ in range(q_size):
            y, x = q.popleft()

            for dy, dx in DIRECTIONS:
                ny, nx = dy + y, dx + x

                if (
                    ny >= H
                    or nx >= W
                    or ny < 0
                    or nx < 0
                    or v[ny][nx]
                    or _map[ny][nx] == "x"
                ):
                    continue

                q.append((ny, nx))
                v[ny][nx] = True
                if _map[ny][nx].isnumeric():
                    dists[start[2]][(1 << int(_map[ny][nx]))] = t


def calc_min_dist(dp: list, idx: int, v: int):
    if v == (1 << dust_cnt) - 1:
        return 0

    if dp[idx][v] != INF:
        return dp[idx][v]

    tmp = INF
    for i in range(dust_cnt):
        if v & (1 << i):
            continue
        tmp = min(tmp, calc_min_dist(dp, i, v | (1 << i)) + dp[idx][1 << i])
    dp[idx][v] = tmp
    return dp[idx][v]


def main():
    global dust_cnt
    ans = []
    while True:
        cost = INF
        W, H = map(int, input().split())
        if W == 0 and H == 0:
            print("\n".join(map(str, ans)))
            return
        _map, vacuum_and_dusts = [], []
        init(W, H, _map, vacuum_and_dusts)
        vacuum_and_dusts.sort(key=lambda arr: arr[2])  # 순서대로 정렬
        dust_cnt = len(vacuum_and_dusts)
        dp = [[INF] * (1 << dust_cnt) for _ in range(dust_cnt)]
        for elem in vacuum_and_dusts:
            BFS(H, W, _map, dp, elem)

        cost = calc_min_dist(dp, 0, 1)

        ans.append(cost if cost != INF else -1)


if __name__ == "__main__":
    main()
