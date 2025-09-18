import sys


# print = sys.stdout.write
input = sys.stdin.readline

N, K = map(int, input().split())
lan_cables = sorted([int(input().rstrip()) for _ in range(N)])


def main():
    lo, hi = 0, lan_cables[-1] + 1

    while lo + 1 < hi:
        mid = (lo + hi) >> 1

        cnt = sum([lan_cable // mid for lan_cable in lan_cables])

        if cnt >= K:
            lo = mid
            continue
        hi = mid

    print(lo)


if __name__ == "__main__":
    main()
