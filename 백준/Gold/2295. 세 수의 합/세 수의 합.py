import sys

# print = sys.stdout.write
input = sys.stdin.readline


N = int(input().rstrip())
numbers = sorted([int(input().rstrip()) for _ in range(N)])
_sum = set()
for x in numbers:
    for y in numbers:
        _sum.add(x + y)


def main():
    for i in range(N - 1, -1, -1):
        target = numbers[i]

        for j in range(i):
            if target - numbers[j] in _sum:
                print(target)
                return


if __name__ == "__main__":
    main()
