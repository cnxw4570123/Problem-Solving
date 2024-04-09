import sys
from collections import deque


# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline
DIRECTIONS = ((0, 1), (0, -1), (-1, 0), (1, 0))  # 동서북남
ORG = [(0, 2, 5, 3), (0, 1, 5, 4)]
ROLLING = [(2, 5, 3, 0), (3, 0, 2, 5), (1, 5, 4, 0), (4, 0, 1, 5)]

N, M, y, x, K = map(int, input().split())
maps = [list(map(int, input().split())) for _ in range(N)]
moves = list(map(int, input().split()))


def main():
    ans = []
    global y, x
    cur = (y, x, [0, 0, 0, 0, 0, 0])  # 좌표 + 주사위

    for move in moves:
        y, x, dice = cur
        ny, nx = y + DIRECTIONS[move - 1][0], x + DIRECTIONS[move - 1][1]
        if ny >= N or nx >= M or ny < 0 or nx < 0:
            continue
        roll(move - 1, dice)

        if maps[ny][nx]:  # 0이 아닌 칸 -> 바닥면으로 복사
            dice[5] = maps[ny][nx]
            maps[ny][nx] = 0
        else:
            maps[ny][nx] = dice[5]

        ans.append(dice[0])
        cur = (ny, nx, dice)
    print("\n".join(map(str, ans)))


def roll(dir, dice):
    next_dice = deque()
    for idx in ORG[dir // 2]:
        next_dice.append(dice[idx])

    for i in range(4):
        dice[ROLLING[dir][i]] = next_dice[i]


if __name__ == "__main__":
    main()
