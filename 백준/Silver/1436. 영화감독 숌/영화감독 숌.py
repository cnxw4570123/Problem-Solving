import sys

# print = sys.stdout.write
input = sys.stdin.readline


N = int(input().rstrip())


def main():
    cnt, start = 0, 665
    while cnt != N:
        start += 1
        if "666" in str(start):
            cnt += 1

    print(start)


if __name__ == "__main__":
    main()
