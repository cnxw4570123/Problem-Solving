import sys

# print = sys.stdout.write
input = sys.stdin.readline


def main():
    init()
    digits = 0

    if cnt < K:
        print(-1)
        return

    while digit_cnts[digits] < K:
        digits += 1

    if K == digit_cnts[digits]:
        print(9)
        return

    temp = K - digit_cnts[digits - 1]
    offset = temp - 1
    num = 10 ** (digits - 1) + offset // digits
    idx = offset % digits
    print(str(num)[idx])


def init():
    global N, K, digit_cnts, cnt
    N, K = map(int, input().split())
    digit_cnts = [0]
    for i in range(1, 10):
        cnts = (9 * 10 ** (i - 1)) * i
        digit_cnts.append(cnts + digit_cnts[i - 1])

    cnt = 0
    for i in range(1, 10):
        # 3, 45, 123과 같이 각 상한선보다 작음.
        if 10**i - 1 >= N:
            a = N - 10 ** (i - 1) + 1
            cnt += a * i
            break

        # 9, 99, 999 보다 크면
        cnt += 9 * (10 ** (i - 1)) * i


if __name__ == "__main__":
    main()
