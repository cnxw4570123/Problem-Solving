import sys

# print = sys.stdout.write
input = sys.stdin.readline


N = int(input().rstrip())


def main():
    cnt = 0

    for i in range(1, N + 1):
        for c in str(i):
            if c in ["3", "6", "9"]:
                cnt += 1

    print(cnt)


if __name__ == "__main__":
    main()
