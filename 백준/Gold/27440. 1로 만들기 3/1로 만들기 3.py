import sys
from collections import defaultdict
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
INF = sys.maxsize

N = int(input().rstrip())

v = defaultdict(lambda: INF)

v[N] = 0


def find():
    q = deque([(N, 0)])

    while q:
        num, cnt = q.popleft()

        if num == 1:
            return cnt

        if num - 1 > 1 and v[num - 1] > cnt + 1:
            q.append((num - 1, cnt + 1))
            v[num - 1] = cnt + 1

        if num % 2 == 0 and v[num // 2] > cnt + 1:
            q.append((num // 2, cnt + 1))
            v[num // 2] = cnt + 1

        if num % 3 == 0 and v[num // 3] > cnt + 1:
            q.append((num // 3, cnt + 1))
            v[num // 3] = cnt + 1
            
    return -1


def main():
    print(find())


if __name__ == "__main__":
    main()
