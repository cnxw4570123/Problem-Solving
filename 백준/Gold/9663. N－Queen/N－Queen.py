import sys


# print = sys.stdout.write
input = sys.stdin.readline


N = int(input().rstrip())
ans = 0
history = [-1] * N


def main():
    find(0)
    print(ans)


def find(cnt):
    global ans
    if cnt == N:
        ans += 1
        return

    for i in range(N):
        if not is_satisfied(cnt, i):
            continue
        history[cnt] = i
        find(cnt + 1)


def is_satisfied(y, x):
    for i in range(y):
        if x == history[i]:
            return False
        if y - i == abs(x - history[i]):
            return False
    return True


if __name__ == "__main__":
    main()
