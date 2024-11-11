import sys


# print = sys.stdout.write
input = sys.stdin.readline


MOD = 1_000_000
code = "0" + input().rstrip()
dp = [0] * (len(code) + 1)
# 점화식 처리하기 위한 장치
dp[0] = 1


def main():
    # 순서상 문자열의 두 번째
    # dp의 첫 번째
    # dp[1] = 1

    for idx in range(1, len(code)):
        prev, current = int(code[idx - 1]), int(code[idx])
        if current == 0:
            # 두 부분을 합쳐도 문자 성립이 안되면
            if prev * 10 + current > 26 or prev * 10 + current == 0:
                print(0)
                return
            # 두 부분을 합치면 문자 생성은 가능
            dp[idx] = dp[idx - 2] % MOD
            continue

        # 현재 숫자만 문자로 변환 가능
        if prev * 10 + current > 26 or prev * 10 + current < 10:
            dp[idx] = dp[idx - 1] % MOD
            continue

        # 모두 가능
        dp[idx] = (dp[idx - 2] + dp[idx - 1]) % MOD

    print(dp[len(code) - 1] % MOD)


if __name__ == "__main__":
    main()
