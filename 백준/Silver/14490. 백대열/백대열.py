import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

a, b = map(int, input().split(":"))


def euclidean(a, b):
    if b == 0:
        return a
    return euclidean(b, a % b)


def main():
    r = euclidean(a, b)
    print(f"{a//r}:{b//r}")


if __name__ == "__main__":
    main()
