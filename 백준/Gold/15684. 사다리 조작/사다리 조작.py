import sys

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline

LEFT, RIGHT = 0, 1
N, M, H = map(int, input().split())  # 세로선 개수, 가로선 개수, 세로

ans = sys.maxsize
# 사다리의 [왼쪽, 오른쪽] 연결여부를 저장
edges = [
    [[0, 0] for _ in range(N + 1)] for _ in range(H + 1)
]  # 1 ~ H칸까지 사다리 설치 가능
for _ in range(M):
    row, col = map(int, input().split())
    # 해당 높이에 오른쪽 가로선과 연결
    edges[row][col][RIGHT] = col + 1
    edges[row][col + 1][LEFT] = col


def main():
    if M == 0:
        print(0)
        return
    find(0, 1)
    print(-1 if ans == sys.maxsize else ans)


def find(count, s_row):
    global ans
    if test():
        ans = min(ans, count)
        return

    # 3개 초과의 사다리를 놓을 수 없다
    if count == 3:
        return

    # 레벨
    for row in range(s_row, H + 1):
        for col in range(1, N):
            # 사다리가 연결되어 있으면
            if can_not_set_ladder(row, col):
                continue
            edges[row][col][RIGHT], edges[row][col + 1][LEFT] = col + 1, col
            find(count + 1, row)
            edges[row][col][RIGHT] = edges[row][col + 1][LEFT] = 0


def can_not_set_ladder(row, col):
    # 현재 사다리가 한쪽이라도 연결되어 있으면
    # 또는 오른쪽에 사다리가 연결되어 있으면
    return edges[row][col].count(0) != 2 or edges[row][col + 1][RIGHT]


def test():
    for i in range(1, N + 1):
        if not move(i):
            return False
    return True


def move(start):
    row, col = 1, start
    while row != H + 1:
        if edges[row][col][LEFT]:
            col = edges[row][col][LEFT]
        elif edges[row][col][RIGHT]:
            col = edges[row][col][RIGHT]
        row += 1
    return start == col


if __name__ == "__main__":
    main()
