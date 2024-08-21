import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
#f = open("input.txt", "rt")
#input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

T = int(input().rstrip())


def main():
    ans = []
    for _ in range(T):
        N = int(input().rstrip())
        _sum = 0
        stocks = list(enumerate((map(int, input().split())), 1))

        highest = sorted([stock for stock in stocks], key=lambda x: (x[1], x[0]))

        _max = highest.pop()
        for stock in stocks:
            order, price = stock

            while highest and (_max[0] < order or _max[1] < price):
                _max = highest.pop()

            _sum += _max[1] - price

        ans.append(_sum)

    print("\n".join(map(str, ans)))


if __name__ == "__main__":
    main()
