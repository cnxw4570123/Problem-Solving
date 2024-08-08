import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

N = int(input().rstrip())

q = deque([i for i in range(10)])
v = set(q)


def BFS():
    cnt = 0
    while q:
        num = q.popleft()
        cnt += 1

        if cnt == N:
            return num

        for i in range(num % 10):
            next = num * 10 + i
            if next in v:
                continue
            q.append(next)

    return -1


def main():
    print(BFS())


if __name__ == "__main__":
    main()
