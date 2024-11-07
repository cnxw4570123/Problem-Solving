import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

JESANG, VOLCANO = 0, 1
DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)

M, N, V = map(int, input().split())
Y, X = map(int, input().split())

_map = [list(map(int, input().split())) for _ in range(M)]
visit = [[[False] * N for _ in range(M)] for _ in range(2)]

jesang_q = deque([(Y - 1, X - 1)])
visit[JESANG][Y - 1][X - 1] = True
volcano_q = deque()

volcanoes = []
for _ in range(V):
    y, x, t = tuple(map(int, input().split()))
    volcanoes.append((y - 1, x - 1, t))
    _map[y - 1][x - 1] = -1

volcanoes.sort(key=lambda v: v[2])

ans = [_map[Y - 1][X - 1], 1]


def main():
    BFS()
    print(f"{ans[0]} {ans[1] - 1}")


def BFS():
    t = 0
    while jesang_q:
        # 분화 시간이 된 화산 추가
        while volcanoes and volcanoes[0][2] == t:
            vy, vx, _ = volcanoes.pop(0)
            if visit[VOLCANO][vy][vx]:
                continue

            visit[VOLCANO][vy][vx] = True
            volcano_q.append((vy, vx))

        t += 1
        v_size, j_size = len(volcano_q), len(jesang_q)

        # 화산 먼저
        for _ in range(v_size):
            vy, vx = volcano_q.popleft()
            for dy, dx in DIRECTIONS:
                v_ny, v_nx = vy + dy, vx + dx
                if can_not_reach(v_ny, v_nx) or visit[VOLCANO][v_ny][v_nx]:
                    continue
                visit[VOLCANO][v_ny][v_nx] = True
                volcano_q.append((v_ny, v_nx))

        # 재상
        for _ in range(j_size):
            jy, jx = jesang_q.popleft()
            if _map[jy][jx] > ans[0]:
                ans[0], ans[1] = _map[jy][jx], t
            elif ans[0] == _map[jy][jx] and t < ans[1]:
                ans[1] = t

            for dy, dx in DIRECTIONS:
                j_ny, j_nx = dy + jy, dx + jx
                # 재상이 또는 화산이 방문시 패스
                if (
                    can_not_reach(j_ny, j_nx)
                    or any(visit[i][j_ny][j_nx] for i in range(2))
                    or _map[j_ny][j_nx] == -1
                ):
                    continue
                visit[JESANG][j_ny][j_nx] = True
                jesang_q.append((j_ny, j_nx))


def can_not_reach(y, x):
    return y >= M or x >= N or y < 0 or x < 0


if __name__ == "__main__":
    main()
