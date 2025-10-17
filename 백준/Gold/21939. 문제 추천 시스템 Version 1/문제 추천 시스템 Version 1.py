import sys
from heapq import heapify, heappush, heappop

# print = sys.stdout.write
input = sys.stdin.readline


def main():
    global deleted, level
    init()
    ans = []
    for _ in range(M):
        command, *args = input().split()
        if command == "add":
            p, l = map(int, args)
            level[p] = l
            heappush(max_problems, (-l, -p))
            heappush(min_problems, (l, p))
            continue

        if command == "recommend":
            sign = -int(*args)

            if sign == -1:
                lazy_delete(max_problems)
                ans.append(abs(max_problems[0][1]))
            else:
                lazy_delete(min_problems)
                ans.append(abs(min_problems[0][1]))
            continue

        if command == "solved":
            p = int(*args)
            deleted.add((level[p], p))

    print("\n".join(map(str, ans)))


def init():
    global N, M, min_problems, max_problems, deleted, level
    deleted, level = set(), {}
    N = int(input().rstrip())
    min_problems = []
    for _ in range(N):
        p, l = map(int, input().split())
        level[p] = l
        min_problems.append((l, p))

    max_problems = [(-l, -p) for l, p in min_problems]
    heapify(max_problems)
    heapify(min_problems)
    M = int(input().rstrip())


def lazy_delete(hq):
    while hq:
        l, p = hq[0]
        key = (abs(l), abs(p))
        if key in deleted:
            heappop(hq)
            continue
        break


if __name__ == "__main__":
    main()
