import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

INF = float("inf")

N, K = map(int, input().split())

friends = [int(input().rstrip()) for _ in range(N)]
min_time = friends[-1] - friends[0] + 1
gap = sorted([friends[i] - friends[i - 1] - 1 for i in range(1, N)])


def main():
    global min_time, K
    while gap and K > 1:
        K -= 1
        min_time -= gap.pop()

    print(min_time)


if __name__ == "__main__":
    main()
