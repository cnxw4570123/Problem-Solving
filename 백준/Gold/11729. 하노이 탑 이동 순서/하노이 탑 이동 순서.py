import sys


# print = sys.stdout.write
input = sys.stdin.readline

N = int(input().rstrip())
ans = []


def main():
    hanoi(N, 1, 2, 3)
    print(len(ans))
    print("\n".join(map(str, ans)))


def hanoi(cnt, src, temp, dest):
    if cnt == 0:
        return

    hanoi(cnt - 1, src, dest, temp)
    ans.append(f"{src} {dest}")
    hanoi(cnt - 1, temp, src, dest)


if __name__ == "__main__":
    main()
