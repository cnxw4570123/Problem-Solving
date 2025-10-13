import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)
doors = {chr(i + 65) for i in range(6)}
keys = {chr(i + 97) for i in range(6)}
q = deque()

N, M = map(int, input().split())
board = []
v = [[[False] * M for _ in range(N)] for _ in range((1 << len(keys)) + 1)]

for i in range(N):
    row = list(input().rstrip())
    for j in range(len(row)):
        if row[j] == "0":
            q.append((i, j, 0))
            v[0][i][j] = True
            continue
    board.append(row)


def main():
    print(BFS())


def BFS():
    move = -1
    while q:
        move += 1
        q_size = len(q)

        for _ in range(q_size):
            y, x, key = q.popleft()

            if board[y][x] == "1":
                return move

            for dy, dx in DIRECTIONS:
                ny, nx = dy + y, dx + x
                if can_not_move(ny, nx, key):
                    continue

                if board[ny][nx] in doors:
                    need_key = 1 << (ord(board[ny][nx].lower()) - 97)
                    # 키를 가지고 있으면
                    # 1001 & 10 ==> 0 : 문 못 엶
                    if key & need_key == need_key:
                        q.append((ny, nx, key))
                        v[key][ny][nx] = True

                    continue

                if board[ny][nx] in keys:
                    key_acquired = 1 << (ord(board[ny][nx]) - 97)
                    q.append((ny, nx, key | key_acquired))
                    v[key | key_acquired][ny][nx] = True
                    continue

                q.append((ny, nx, key))
                v[key][ny][nx] = True

    return -1


def can_not_move(y, x, key):
    return y >= N or x >= M or y < 0 or x < 0 or v[key][y][x] or board[y][x] == "#"


if __name__ == "__main__":
    main()
