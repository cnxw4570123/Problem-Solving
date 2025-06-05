import sys


# print = sys.stdout.write
input = sys.stdin.readline


N, K = map(int, input().split())
drinks = sorted([int(input().rstrip()) for _ in range(N)])


def main():
    left, right = 1, drinks[-1]

    while left <= right:
        mid = (left + right) >> 1

        people = 0
        for drink in drinks:
            people += drink // mid

        if people < K:
            right = mid - 1
            continue

        left = mid + 1

    print(right)


if __name__ == "__main__":
    main()
