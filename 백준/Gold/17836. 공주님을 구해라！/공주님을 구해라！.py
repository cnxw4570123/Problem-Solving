import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)
ROAD, WALL, GRAM = 0, 1, 2

N, M, T = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

v = [[0] * M for _ in range(N)]


def BFS():
    q = deque([(0, 0, 0)])
    v[0][0] |= 1
    t = -1
    while q:
        t += 1
        if t > T:
            break
        q_size = len(q)
        for _ in range(q_size):
            y, x, has_gram = q.popleft()
            if (y, x) == (N - 1, M - 1):
                return t

            for dy, dx in DIRECTIONS:
                ny, nx, n_has_gram = dy + y, dx + x, has_gram
                if can_not_access(ny, nx, n_has_gram):
                    continue
                if not n_has_gram and board[ny][nx] == WALL:
                    continue

                if board[ny][nx] == GRAM:
                    n_has_gram |= 1

                v[ny][nx] |= 1 << n_has_gram

                q.append((ny, nx, n_has_gram))
    return sys.maxsize


def can_not_access(y, x, has_gram):
    return y >= N or x >= M or y < 0 or x < 0 or (v[y][x] & 1 << has_gram)


def main():
    ans = BFS()
    print(ans if ans <= T else "Fail")


if __name__ == "__main__":
    main()
