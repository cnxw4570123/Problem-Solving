import sys


sys.setrecursionlimit(1000)
input = sys.stdin.readline
# print = sys.stdout.write


def main():
    ans = []
    N, M = map(int, input().rstrip().split())
    number = input().strip()
    start = 0
    for i in range(N - M, 0, -1):
        max = "0"
        for idx in range(start, N - i + 1):
            if number[idx] == "9":
                max = "9"
                start = idx + 1
                break
            if max < number[idx]:
                max = number[idx]
                start = idx + 1
        ans.append(max)
    print("".join(ans))


if __name__ == "__main__":
    main()
