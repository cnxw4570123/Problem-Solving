import sys

# print = sys.stdout.write
input = sys.stdin.readline

N = int(input().rstrip())
numbers = [int(input().rstrip()) for _ in range(N)]
dp = [0 for _ in range(11)]


def main():
    for number in numbers:
        print(find(number))


def find(num: int):
    if num == 0:
        return 1

    if dp[num]:
        return dp[num]

    res = 0
    if num >= 1:
        res += find(num - 1)

    if num >= 2:
        res += find(num - 2)

    if num >= 3:
        res += find(num - 3)

    dp[num] = res
    return res


if __name__ == "__main__":
    main()
