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
            current_sum = numbers[i] + numbers[left] + numbers[right]
            if abs(current_sum) < abs(gap):
                ans = [numbers[i], numbers[left], numbers[right]]
                gap = abs(current_sum)

            if current_sum < 0:
                left += 1
            elif current_sum > 0:
                right -= 1
            else:
                print(*ans)
                return

    print(*ans)


if __name__ == "__main__":
    main()
