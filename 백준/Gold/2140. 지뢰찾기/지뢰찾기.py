import sys
from itertools import combinations

# print = sys.stdout.write
input = sys.stdin.readline

DIRECTIONS = (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1)
N = int(input().rstrip())

board = [list(input().rstrip()) for _ in range(N)]


ans = 0
length = 4 * N - 4

candidates = []


def init():
    candidates = []
    for i in range(N):
        for j in range(N):
            if i in (0, N - 1) or j in (0, N - 1):
                candidates.append((int(board[i][j]), i, j))

    return candidates


def check(cnt, y, x):
    candidate = []
    for dy, dx in DIRECTIONS:
        ny, nx = dy + y, dx + x
        if ny < 1 or nx < 1 or ny >= N - 1 or nx >= N - 1:
            continue

        if board[ny][nx] == "#":
            board[ny][nx] = " "  # 지뢰 찾기 준비
            candidate.append((ny, nx))
            continue
        if board[ny][nx] == "*":  # 이미 지뢰가 있다면
            cnt -= 1

    return cnt, candidate


def DFS(idx, current):
    global ans
    if idx >= length:
        ans = max(ans, current)
        return

    for i in range(idx, length):
        cnt, y, x = candidates[i]

        possible_mines_cnt, mine_pos = check(cnt, y, x)

        # 지뢰 개수가 맞지 않으면 게임 실패한 것.
        if possible_mines_cnt < 0 or len(mine_pos) < possible_mines_cnt:
            return

        if possible_mines_cnt == 0:
            DFS(i + 1, current)
            return

        for lst in combinations(mine_pos, possible_mines_cnt):
            for ny, nx in lst:
                board[ny][nx] = "*"  # 지뢰 있다고 가정
            DFS(i + 1, current + possible_mines_cnt)


def main():
    global candidates, ans
    candidates = init()

    DFS(0, 0)
    if N >= 5:
        ans += (N - 4) ** 2
    print(ans)


if __name__ == "__main__":
    main()
