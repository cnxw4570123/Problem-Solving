import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

TURN = (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1)
MOVE = (-1, 0), (1, 0), (0, -1), (0, 1)
END = ("E", "E", "E")
N = int(input().rstrip())
board, start = [], []

for i in range(N):
    row = list(input().rstrip())
    for j in range(N):
        if row[j] == "B":
            start.extend((i, j))
    board.append(row)
if start[0] == start[2] == start[4]:
    start.append(0)  # 가로로 한 줄 -> y값 고정
else:
    start.append(1)  # 세로로 한 줄 -> x값 고정

start = tuple(start)


def can_turn(y, x):
    for dy, dx in TURN:
        ny, nx = dy + y, dx + x
        # 물리적으로 가능한지만 확인
        if ny >= N or nx >= N or ny < 0 or nx < 0 or board[ny][nx] == "1":
            return False

    return True


def can_move(y, x):
    for i in range(3):
        ny, nx = y[i], x[i]
        if ny >= N or nx >= N or ny < 0 or nx < 0 or board[ny][nx] == "1":
            return False

    return True


def BFS():
    q = deque([start])
    t = 0
    v = set()
    v.add(start)
    while q:
        q_size = len(q)
        for _ in range(q_size):
            y1, x1, y2, x2, y3, x3, status = q.popleft()

            if (board[y1][x1], board[y2][x2], board[y3][x3]) == END:
                return t

            for dy, dx in MOVE:
                ny, nx = (dy + y1, dy + y2, dy + y3), (
                    dx + x1,
                    dx + x2,
                    dx + x3,
                )
                if not can_move(ny, nx):
                    continue

                next = (ny[0], nx[0], ny[1], nx[1], ny[2], nx[2], status)

                if next in v:
                    continue

                q.append(next)
                v.add(next)

            if can_turn(y2, x2):
                nstatus = (status + 1) % 2
                # 가로에서 세로로 변경
                if nstatus == 1:
                    next = (y2 - 1, x2, y2, x2, y2 + 1, x2, nstatus)

                # 세로로 변경
                else:
                    next = (y2, x2 - 1, y2, x2, y2, x2 + 1, nstatus)

                if next in v:
                    continue
                q.append(next)
                v.add(next)

        t += 1
    return 0


def main():
    print(BFS())


if __name__ == "__main__":
    main()
