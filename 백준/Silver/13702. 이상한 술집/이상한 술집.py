import sys

# print = sys.stdout.write
input = sys.stdin.readline

N, K = map(int, input().split())
pots = sorted([int(input().rstrip()) for _ in range(N)])


def main():
    lo, hi = 0, pots[-1] + 1

    while lo + 1 < hi:
        mid = (lo + hi) >> 1

        cnt = sum([pot // mid for pot in pots])

        if cnt >= K:
            lo = mid
            continue
        hi = mid

    print(lo)


if __name__ == "__main__":
    main()
