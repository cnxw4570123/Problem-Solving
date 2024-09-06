import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
#f = open("input.txt", "rt")
#input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

N = int(input().rstrip())
nums = [0] + list(map(int, input().split()))

dp = [[0] * (N + 1) for _ in range(2)]
dp[0][1] = dp[1][1] = nums[1]


def main():
    if N == 1:
        print(nums[1])
        return

    ans = -100_000_001
    for i in range(2, N + 1):
        dp[0][i] = max(dp[0][i - 1] + nums[i], nums[i])
        dp[1][i] = max(dp[0][i - 1], dp[1][i - 1] + nums[i])
        ans = max(ans, dp[0][i], dp[1][i])

    print(ans)


if __name__ == "__main__":
    main()
