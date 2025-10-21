import sys

# print = sys.stdout.write
input = sys.stdin.readline


def main():
    init()
    ans = []
    if N == 3:
        print(-1)
        return

    for i in range(2, N):
        ans.append(i)
        if i == N // 2:
            ans.extend([1, N])

    print("\n".join(map(str, ans)))


def init():
    global N
    N = int(input().rstrip())


if __name__ == "__main__":
    main()
