import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

T = int(input().rstrip())


def main():
    ans = []
    for _ in range(T):
        N = int(input().rstrip())
        _sum = 0
        stocks = list(map(int, input().split()))

        _max = 0
        for stock in reversed(stocks):
            if _max < stock:
                _max = stock
                continue

            _sum += _max - stock

        ans.append(_sum)

    print("\n".join(map(str, ans)))


if __name__ == "__main__":
    main()
