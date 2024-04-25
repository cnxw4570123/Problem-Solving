import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

N = int(input().rstrip())
heights = [int(input().rstrip()) for _ in range(N)]
s = []  # 현재 건물이 보이는 건물 수와 인덱스


def main():
    ans = 0
    for i in range(N - 1, -1, -1):
        if not s:
            s.append((0, heights[i]))

        else:
            cnt = 0
            while s and s[-1][1] < heights[i]:
                cnt += s.pop()[0] + 1  # 해당 건물이 벤치마킹하는 건물 수 + 해당 건물
            s.append((cnt, heights[i]))
            ans += cnt

    print(ans)


if __name__ == "__main__":
    main()
