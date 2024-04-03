import sys
import bisect

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline

N, M = map(int, input().split())
arr = [int(input().rstrip()) for _ in range(N)]
arr.sort()


def main():
    ans = sys.maxsize
    for i in range(N - 1):
        idx = bisect.bisect_left(arr, arr[i] + M, i + 1)
        if idx >= N:
            continue
        if arr[idx] - arr[i] == M:
            ans = M
            break
        elif arr[idx] - arr[i] > M:
            ans = min(ans, arr[idx] - arr[i])
    print(ans)


if __name__ == "__main__":
    main()
