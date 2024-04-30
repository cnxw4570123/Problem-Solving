import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

str1, str2 = input().rstrip(), input().rstrip()
len1, len2 = len(str1), len(str2)
lcs = [[0] * (len(str2) + 1) for _ in range(len(str1) + 1)]


def main():
    ans = 0
    for i in range(1, len1 + 1):
        for j in range(1, len2 + 1):
            if str1[i - 1] != str2[j - 1]:
                continue
            lcs[i][j] = lcs[i - 1][j - 1] + 1
            ans = max(lcs[i][j], ans)
            
    print(ans)


if __name__ == "__main__":
    main()
