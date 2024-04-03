import sys

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline

N, M = map(int, input().split())
arr = [int(input().rstrip()) for _ in range(N)]
arr.sort()


def main():
    ans = sys.maxsize
    start, end = 0, 0
    while start <= end and end < N:
        gap = arr[end] - arr[start]
        if gap == M:
            print(M)
            return
        if gap > M:
            ans = min(ans, gap)
            start += 1
        else:
            end += 1
    print(ans)


if __name__ == "__main__":
    main()
