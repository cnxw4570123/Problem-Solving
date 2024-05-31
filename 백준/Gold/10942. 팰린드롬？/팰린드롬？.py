import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
N = int(input().rstrip())
arr = [0] + list(map(int, input().split()))
M = int(input().rstrip())
dp = [[1 if i == j else -1 for j in range(N + 1)] for i in range(N + 1)]


def find(s, e):
    if dp[s][e] != -1:
        return dp[s][e]

    if s + 1 == e:
        dp[s][e] = is_same(s, e)
        return dp[s][e]

    dp[s][e] = find(s + 1, e - 1) & is_same(s, e)
    return dp[s][e]


def is_same(s, e):
    return 1 if arr[s] == arr[e] else 0


def main():
    for _ in range(M):
        s, e = map(int, input().split())
        print(find(s, e))


if __name__ == "__main__":
    main()
