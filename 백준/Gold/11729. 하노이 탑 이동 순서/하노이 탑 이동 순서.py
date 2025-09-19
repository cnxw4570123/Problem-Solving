import sys
from collections import defaultdict

# print = sys.stdout.write
input = sys.stdin.readline

N = int(input().rstrip())
memo = defaultdict(str)


def main():
    print((1 << N) - 1)
    print(hanoi(N, 1, 2, 3))


def hanoi(cnt, src, temp, dest):
    key = (cnt, src, temp, dest)

    if memo[key]:
        return memo[key]

    if cnt == 1:
        return f"{src} {dest}"

    res = "\n".join(
        [
            hanoi(cnt - 1, src, dest, temp),
            f"{src} {dest}",
            hanoi(cnt - 1, temp, src, dest),
        ]
    )
    memo[key] = res
    return res


if __name__ == "__main__":
    main()
