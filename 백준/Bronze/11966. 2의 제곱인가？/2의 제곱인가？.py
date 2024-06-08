import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

N = int(input().rstrip())


def main():
    start = 1
    for i in range(31):
        if N == start:
            print(1)
            return
        start <<= 1

    print(0)


if __name__ == "__main__":
    main()
