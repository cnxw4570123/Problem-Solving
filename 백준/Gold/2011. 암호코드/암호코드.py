import sys


# print = sys.stdout.write
input = sys.stdin.readline

MOD = 1_000_000
code = [0] + list(map(int, input().rstrip()))
dp = [0] * (len(code) + 1)
# 점화식 처리하기 위한 장치
dp[0] = 1


def main():
    if code[1] == 0:
        print(0)
        return

    for idx in range(1, len(code)):
        # 길이를 늘려줬으므로 첫 번째 자리부터 시작가능
        if code[idx] > 0:
            dp[idx] += dp[idx - 1]

        temp = code[idx - 1] * 10 + code[idx]
        if 10 <= temp <= 26:
            dp[idx] += dp[idx - 2]
        dp[idx] %= MOD

    # 강제로 한 글자 늘렸으니 -1 해줘야함
    print(dp[len(code) - 1] % MOD)


if __name__ == "__main__":
    main()
