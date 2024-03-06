import sys


# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline

N = int(input().rstrip())
numbers = sorted(list(map(int, input().split())))
can_make = []


def main():
    ans = 0
    for i in range(len(numbers)):
        temp = numbers[:i] + numbers[i + 1 :]  # 항상 특정 개수 유지
        start, end = 0, len(temp) - 1
        while start < end:
            cur = temp[start] + temp[end]
            if cur == numbers[i]:
                ans += 1
                break
            if cur < numbers[i]:
                start += 1
            else:
                end -= 1
    print(ans)


if __name__ == "__main__":
    main()
