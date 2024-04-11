import sys

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline

DIRECTIONS = ((-1, 0), (1, 0), (0, -1), (0, 1))
MAX_TIME = 1000

N, M, K = map(int, input().split())  # 격자 크기, 상어 마리수, 냄새 잔존 기간
sharks = []
pheromones = [[[0, 0]] * N for _ in range(N)]
ocean = []
for i in range(N):
    row = list(map(int, input().split()))
    for j in range(N):
        if row[j] != 0:
            pheromones[i][j] = [row[j], K]
            sharks.append([i, j, row[j], 0])
    ocean.append(row)
sharks.sort(key=lambda shark: shark[2])  # 번호순으로 정렬
for idx, dir in enumerate(list(map(int, input().split())), 0):
    sharks[idx][3] = dir
    sharks[idx] = tuple(sharks[idx])

priority = [[]] + [
    [list(map(int, input().split())) for _ in range(4)] for _ in range(M)
]


def main():
    ans = proceed()
    print(-1 if ans > MAX_TIME else ans)


def proceed():
    t = 0
    while len(sharks) != 1 and t <= MAX_TIME:
        t += 1
        q_size = len(sharks)
        for _ in range(q_size):
            y, x, num, dir = sharks.pop(0)
            move(y, x, num, dir, priority[num])
        set_pheromones()
    return t


def set_pheromones():
    for i in range(N):
        for j in range(N):
            if ocean[i][j]:
                pheromones[i][j] = [ocean[i][j], K]
                continue
            if pheromones[i][j][1] == 0:
                continue
            if pheromones[i][j][1] - 1 == 0:
                pheromones[i][j] = [0, 0]
                continue
            pheromones[i][j][1] -= 1


def move(y, x, num, dir, priority):
    res = check(y, x, num, priority[dir - 1])
    if not res:
        return
    ocean[y][x] = 0
    ny, nx, ndir = res
    # 만약 해당 칸에 있는 상어가 더 작은 숫자면
    if ocean[ny][nx] and ocean[ny][nx] < num:
        return

    # 해당 칸에 있는 상어보다 현재 상어가 더 작은 숫자면
    if ocean[ny][nx]:
        for shark in sharks:
            if shark[2] == ocean[ny][nx]:
                sharks.remove(shark)
                break

    ocean[ny][nx] = num
    sharks.append((ny, nx, num, ndir))


# 냄새기준으로 판별
def check(y, x, num, move):
    # 탐색을 우선순위 기준으로
    possible_col = []
    for idx in move:
        ny, nx = DIRECTIONS[idx - 1][0] + y, DIRECTIONS[idx - 1][1] + x
        if ny >= N or nx >= N or ny < 0 or nx < 0:
            continue
        if pheromones[ny][nx][0] == 0:
            return (ny, nx, idx)
        if pheromones[ny][nx][0] != num:
            continue
        possible_col.append((ny, nx, idx))

    return possible_col[0] if possible_col else ()


if __name__ == "__main__":
    main()
