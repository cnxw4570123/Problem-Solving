import sys

# print = sys.stdout.write
input = sys.stdin.readline

INF = sys.maxsize
DIRECTIONS = (-1, 0), (0, 0), (1, 0), (0, -1), (0, 1)
SWITCH = {"#": "O", "O": "#"}


def main():
    init()
    ans = 0
    for tries in range(1 << 10 + 1):
        nboard = [[board[i][j] for i in range(10)] for j in range(10)]
        cnt = 0

        for col in range(10):
            if not tries & (1 << col):  # 만약 눌렀다면
                continue
            change_status(nboard, 0, col)  # 실제 행 변화
            cnt += 1

        for row in range(1, 10):
            for col in range(10):
                if nboard[row - 1][col] == "#":
                    continue
                change_status(nboard, row, col)
                cnt += 1

        if nboard[-1].count("#") != 10:
            continue

        ans = max(cnt, ans)
    print(ans)


def init():
    global board
    board = [list(input().rstrip()) for _ in range(10)]


def change_status(board, y, x):
    for dy, dx in DIRECTIONS:
        ny, nx = dy + y, dx + x
        if ny >= 10 or nx >= 10 or ny < 0 or nx < 0:
            continue
        board[ny][nx] = SWITCH[board[ny][nx]]


if __name__ == "__main__":
    main()
