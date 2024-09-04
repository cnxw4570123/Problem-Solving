import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
#f = open("input.txt", "rt")
#input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
INF = 2_000_000
ROAD, WALL = "0", "1"
DAY, NIGHT = 0, 1
DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)

N, M, K = map(int, input().split())
_map = [list(input().rstrip()) for _ in range(N)]
v = [[K + 1] * M for _ in range(N)]


def BFS():
    q = deque([(0, 0, 0)])
    v[0][0] = 0
    time, move = DAY, 0

    while q:
        move += 1
        q_size = len(q)
        for _ in range(q_size):
            y, x, hammer_cnt = q.popleft()

            if (y, x) == (N - 1, M - 1):
                return move

            for dy, dx in DIRECTIONS:
                ny, nx = dy + y, dx + x
                if ny >= N or nx >= M or ny < 0 or nx < 0 or v[ny][nx] <= hammer_cnt:
                    continue

                if _map[ny][nx] == ROAD:
                    v[ny][nx] = hammer_cnt
                    q.append((ny, nx, hammer_cnt))
                    continue

                # 벽이면
                if hammer_cnt < K:
                    if time == DAY:
                        q.append((ny, nx, hammer_cnt + 1))
                        v[ny][nx] = hammer_cnt + 1
                        continue
                    q.append((y, x, hammer_cnt))

        time = (time + 1) % 2

    return -1


def main():
    print(BFS())


if __name__ == "__main__":
    main()
