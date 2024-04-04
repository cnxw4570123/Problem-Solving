import sys

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline


LEFT, RIGHT = 0, 1
ADJ_LEFT, ADJ_RIGHT = 6, 2

cogwheels = (
    [list("00000000")] + [list(input().rstrip()) for _ in range(4)] + [list("00000000")]
)
K = int(input().rstrip())


def main():
    for _ in range(K):
        target, dir = map(int, input().split())
        if dir == 1:  # 시계
            move(target, RIGHT, set())
        else:
            move(target, LEFT, set())

    ans = 0
    for i in range(1, 5):
        if cogwheels[i][0] == "1":
            ans += 2 ** (i - 1)

    print(int(ans))


def move(target, dir, v=set()):
    # 기저 탈출 조건
    if target > 4 or target < 1 or target in v:
        return
    v.add(target)
    if check(target, target + 1, RIGHT):
        move(target + 1, (dir + 1) % 2, v)
    if check(target, target - 1, LEFT):
        move(target - 1, (dir + 1) % 2, v)

    cur = cogwheels[target]
    if dir == RIGHT:  # 시계방향
        spin = cur.pop()
        cur.insert(0, spin)
        return

    spin = cur.pop(0)
    cur.append(spin)


def check(cur, target, dir):
    if dir == RIGHT:  # 오른쪽 바퀴와 비교할 거면
        return cogwheels[cur][ADJ_RIGHT] != cogwheels[target][ADJ_LEFT]

    return cogwheels[cur][ADJ_LEFT] != cogwheels[target][ADJ_RIGHT]


if __name__ == "__main__":
    main()
