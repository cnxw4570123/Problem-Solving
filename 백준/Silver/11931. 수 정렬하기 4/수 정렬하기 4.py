import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

N = int(input().rstrip())
arr = sorted([int(input().rstrip()) for _ in range(N)], reverse=True)


def main():
    print("\n".join(map(str, arr)))


if __name__ == "__main__":
    main()
