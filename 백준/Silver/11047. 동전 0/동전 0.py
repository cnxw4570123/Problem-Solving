import sys


# print = sys.stdout.write
input = sys.stdin.readline

N, K = map(int, input().split())
coins = [int(input().rstrip()) for _ in range(N)]


def main():
    current, ans = K, 0
    while current:
        coin_idx = binary_search(current)
        cnt = current // coins[coin_idx]

        ans += cnt
        current -= coins[coin_idx] * cnt
    print(ans)


def binary_search(target):
    lo, hi = -1, len(coins)

    while lo + 1 < hi:
        mid = (lo + hi) >> 1

        if coins[mid] <= target:
            lo = mid
        else:
            hi = mid

    return lo


if __name__ == "__main__":
    main()
