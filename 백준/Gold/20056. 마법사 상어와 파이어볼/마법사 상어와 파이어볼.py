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
    if r < 0:
        r += N
    if r >= N:
        r -= N

    if c < 0:
        c += N
    if c >= N:
        c -= N
    return r, c


def move():
    global fireballs
    board = [[[] for _ in range(N)] for _ in range(N)]
    while fireballs:
        r, c, m, s, d = fireballs.pop()
        dr, dc = DIRECTION[d]
        # 보정한 후의 좌표
        # 실제 움직여야 하는 칸

        nr, nc = correction(dr * (s % N) + r, dc * (s % N) + c)
        board[nr][nc].append((m, s, d))

    return board


def mix_and_split(board):
    global fireballs

    # (질량 합계, 속력 합계, 파이어볼 개수, 방향 같은지)
    # 방향 같은 것
    # 들어오는 원소부터 ^ (d % 2)
    for i in range(N):
        for j in range(N):
            if not board[i][j]:
                continue

            if len(board[i][j]) == 1:
                fireballs.append([i, j, *board[i][j][0]])
                continue

            mass, velocity = 0, 0
            for m, s, _ in board[i][j]:
                mass += m
                velocity += s

            if mass < 5:
                continue

            mass //= 5
            velocity //= len(board[i][j])
            same_remainder = all(
                board[i][j][0][2] % 2 == fireball[2] % 2 for fireball in board[i][j]
            )

            divide(i, j, mass, velocity, same_remainder)


def divide(i, j, mass, velocity, same_remainder):
    global fireballs
    start = 0 if same_remainder else 1
    for d in range(start, 8, 2):
        fireballs.append([i, j, mass, velocity, d])


def main():
    for _ in range(K):
        # move
        board = move()
        # 합치고 나누기
        mix_and_split(board)

    ans = sum([fireball[2] for fireball in fireballs])

    print(ans)


if __name__ == "__main__":
    main()
