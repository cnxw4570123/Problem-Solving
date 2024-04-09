import sys

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline
DIRECTIONS = ((0, 1), (0, -1), (-1, 0), (1, 0))  # 동서북남
ROLLING = [
    ((1, 3), (3, 6), (6, 4), (4, 1)),
    ((1, 4), (3, 1), (6, 3), (4, 6)),
    ((1, 5), (2, 1), (6, 2), (5, 6)),
    ((1, 2), (2, 6), (6, 5), (5, 1)),
]

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
        dice = roll(move - 1, dice)

        if maps[ny][nx]:
            dice[5] = maps[ny][nx]
            maps[ny][nx] = 0
        else:
            maps[ny][nx] = dice[5]

        ans.append(dice[0])
        cur = (ny, nx, dice)
    print("\n".join(map(str, ans)))


def roll(dir, dice):
    next_dice = list(dice)
    for current, next in ROLLING[dir]:
        next_dice[next - 1] = dice[current - 1]
    return next_dice


if __name__ == "__main__":
    main()
