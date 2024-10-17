import sys

# print = sys.stdout.write
input = sys.stdin.readline


DIRECTION = (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)

N, M, K = map(int, input().split())
fireballs = []

for _ in range(M):
    r, c, m, s, d = map(int, input().split())
    fireballs.append([r - 1, c - 1, m, s, d])


def correction(r, c):
    if r >= N:
        r -= N
    if r < 0:
        r += N

    if c >= N:
        c -= N
    if c < 0:
        c += N

    return r, c


def move():
    global fireballs

    for i in range(len(fireballs)):
        r, c, _, s, d = fireballs[i]
        dr, dc = DIRECTION[d]
        # 보정한 후의 좌표

        s %= N
        fireballs[i][0], fireballs[i][1] = correction(dr * s + r, dc * s + c)


def mix():
    # (질량 합계, 속력 합계, 파이어볼 개수, 방향, 나머지가 같은지)
    global fireballs
    board = [[()] * N for _ in range(N)]

    while fireballs:
        r, c, m, s, d = fireballs.pop()
        if not board[r][c]:
            board[r][c] = (m, s, 1, d, True)
            continue

        pm, ps, pc, pd, pr = board[r][c]
        # 한 번이라도 나머지가 다르면 [1, 3, 5, 7]
        if not pr:
            board[r][c] = (pm + m, ps + s, pc + 1, pd, pr)
            continue

        board[r][c] = (pm + m, ps + s, pc + 1, pd, (pd % 2) == (d % 2))

    return board


def divide(board):
    global fireballs
    for i in range(N):
        for j in range(N):
            if not board[i][j]:
                continue
            mass, velocity, cnt, d, same_remainder = board[i][j]

            if cnt == 1:
                fireballs.append([i, j, mass, velocity, d])
                continue

            if mass < 5:
                continue

            mass //= 5
            velocity //= cnt

            start = 0 if same_remainder else 1
            for d in range(start, 8, 2):
                fireballs.append([i, j, mass, velocity, d])


def main():
    for _ in range(K):
        # move
        move()
        # 합치기
        board = mix()
        # 나누기
        divide(board)

    ans = sum([fireball[2] for fireball in fireballs])

    print(ans)


if __name__ == "__main__":
    main()
