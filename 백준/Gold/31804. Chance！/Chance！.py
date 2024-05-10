import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

MAX = 1_000_000
A, B = map(int, input().split())
v = [MAX + 1] * (B + 1)


def BFS():
    q = deque([(B, 0, 0)])
    v[B] = 0
    while q:
        cur, cnt, chance = q.popleft()

        if cur == A:
            return cnt

        if cur - 1 >= A and v[cur - 1] > cnt + 1:
            v[cur - 1] = cnt + 1
            q.append((cur - 1, cnt + 1, chance))

        if cur % 2 == 0 and cur // 2 >= A and v[cur // 2] > cnt + 1:
            v[cur // 2] = cnt + 1
            q.append((cur // 2, cnt + 1, chance))

        if chance < 1 and cur % 10 == 0 and cur // 10 >= A and v[cur // 10] > cnt + 1:
            v[cur // 10] = cnt + 1
            q.append((cur // 10, cnt + 1, chance + 1))


def main():
    print(BFS())


if __name__ == "__main__":
    main()
