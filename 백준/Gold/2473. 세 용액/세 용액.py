import sys

# print = sys.stdout.write
input = sys.stdin.readline

N = int(input().rstrip())

numbers = sorted(map(int, input().split()))


def main():
    ans = []
    gap = sys.maxsize
    for i in range(N):
        left, right = i + 1, N - 1

        while left < right:
            if abs(numbers[i] + numbers[left] + numbers[right]) < abs(gap):
                ans = [numbers[i], numbers[left], numbers[right]]
                gap = abs(numbers[i] + numbers[left] + numbers[right])

            if numbers[i] + numbers[left] + numbers[right] < 0:
                left += 1
            else:
                right -= 1
    print(*ans)


if __name__ == "__main__":
    main()
