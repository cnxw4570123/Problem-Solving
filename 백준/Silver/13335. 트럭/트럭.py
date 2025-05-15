import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline


N, W, L = map(int, input().split())
trucks = list(map(int, input().split()))


def main():
    start = trucks.pop(0)
    q = deque([(1, start)])
    time, weight = 1, start

    while True:
        time += 1

        if time - q[0][0] >= W:
            _, w = q.popleft()
            weight -= w

        if trucks and len(q) < W and weight + trucks[0] <= L:
            truck = trucks.pop(0)
            q.append((time, truck))
            weight += truck

        if not q:
            break

    print(time)


if __name__ == "__main__":
    main()
