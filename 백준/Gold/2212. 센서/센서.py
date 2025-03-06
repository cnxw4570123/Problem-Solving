import sys

sys.setrecursionlimit(10**8)
# print = sys.stdout.write
input = sys.stdin.readline

N = int(input().rstrip())
K = int(input().rstrip())

sensors = sorted(list(map(int, input().split())))


def main():
    if N <= K:
        print(0)
        return

    dists = sorted([sensors[i] - sensors[i - 1] for i in range(1, N)])
    print(sum(dists[: N - K]))


if __name__ == "__main__":
    main()
