import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

# 가로인지, 반전되었는지
DIRECTIONS = ((False, False), (False, True), (True, False), (True, True))

N = int(input().rstrip())
board = [list(map(int, input().split())) for _ in range(N)]


ans = 0


def main():
    find(0, board)
    print(ans)
    pass


def find(cnt, board):
    global ans
    if cnt == 5:
        for i in range(N):
            ans = max(max(board[i]), ans)
        return

    for i in range(4):
        nboard = [[0] * (N) for _ in range(N)]
        # 보드를 순회하면서합치는 기능
        calc_each(nboard, find_blocks(board, i), DIRECTIONS[i][0], DIRECTIONS[i][1])
        find(cnt + 1, nboard)


def calc_each(board, blocks, vertical, reverse):
    # 방향에따라 증가값 변화
    # 가로 세로 맞춰서 증감
    # 가로일 경우 col을 중심으로 for문에서 row 증가, dq안에서 col 증가
    # 세로일 경우 row를 중심으로 for문에서 col 증가, dq안에서 row 증가
    # 오른쪽으로
    if vertical and reverse:
        row = 0
        for dq in blocks:
            col = N - 1
            while dq:
                num = dq.pop()
                if dq and num == dq[-1]:
                    num += dq.pop()
                board[row][col] = num
                col -= 1
            row += 1
        return
    # 왼쪽으로
    if vertical:
        row = 0
        for dq in blocks:
            col = 0
            while dq:
                num = dq.popleft()
                if dq and num == dq[0]:
                    num += dq.popleft()
                board[row][col] = num
                col += 1
            row += 1
        return
    # 아래로
    if reverse:
        col = 0
        for dq in blocks:
            row = N - 1
            while dq:
                num = dq.pop()
                if dq and num == dq[-1]:
                    num += dq.pop()
                board[row][col] = num
                row -= 1

            col += 1
        return

    # 위로
    col = 0
    for dq in blocks:
        row = 0
        if len(dq) > 1:
            while dq:
                num = dq.popleft()
                if dq and num == dq[0]:
                    num += dq.popleft()
                board[row][col] = num
                row += 1
        else:
            while dq:
                board[row][col] = dq.popleft()
        col += 1


def find_blocks(board, dir) -> list:
    # 가로, 세로만 확인
    # 데크에 해당 방향으로 원소를 훑어서 전달
    blocks = []
    if dir in (0, 1):
        for i in range(N):
            col = deque()
            # 세로 한 줄
            for j in range(N):
                if board[j][i]:
                    col.append(board[j][i])
            blocks.append(col)
    else:
        for i in range(N):
            col = deque()
            # 가로 한 줄
            for j in range(N):
                if board[i][j]:
                    col.append(board[i][j])
            blocks.append(col)
    return blocks


if __name__ == "__main__":
    main()
