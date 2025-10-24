import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline


def main():
    T = int(input().rstrip())
    for _ in range(T):
        init()
        print(BFS())


def init():
    global N, convenience_stores
    N = int(input().rstrip())
    convenience_stores = [tuple(map(int, input().split())) for _ in range(N + 2)]


def BFS():
    q = deque([convenience_stores[0]])
    v = set([(convenience_stores[0][0], convenience_stores[0][1])])
    while q:
        y, x = q.popleft()

        if (y, x) == (convenience_stores[-1][0], convenience_stores[-1][1]):
            return "happy"

        for next_y, next_x in convenience_stores[1:]:
            if abs(next_y - y) + abs(next_x - x) > 1_000 or (next_y, next_x) in v:
                continue
            v.add((next_y, next_x))
            q.append((next_y, next_x))

    return "sad"


if __name__ == "__main__":
    main()
