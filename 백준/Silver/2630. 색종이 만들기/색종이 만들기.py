import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
colors = [0, 0]

N = int(input().rstrip())
arr = [list(map(int, input().split())) for _ in range(N)]


def check(y, x, size):
    criteria = arr[y][x]

    for i in range(y, y + size):
        for j in range(x, x + size):
            if criteria != arr[i][j]:
                return False

    return True


def is_colored_paper(y, x, size):
    if check(y, x, size):
        colors[arr[y][x]] += 1
        return

    size //= 2
    is_colored_paper(y, x, size)
    is_colored_paper(y, x + size, size)
    is_colored_paper(y + size, x, size)
    is_colored_paper(y + size, x + size, size)


def main():
    is_colored_paper(0, 0, N)
    print("\n".join(map(str, colors)))


if __name__ == "__main__":
    main()
