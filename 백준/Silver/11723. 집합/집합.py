import sys

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline


def main():
    s = 0
    N = int(input().rstrip())
    for _ in range(N):
        com = input().split()
        if com[0] == "add":
            num = int(com[1])
            s |= 1 << num
        elif com[0] == "check":
            num = int(com[1])
            print(1 if s & (1 << num) else 0)
        elif com[0] == "remove":
            num = int(com[1])
            s = s & ~(1 << num)
        elif com[0] == "toggle":
            num = int(com[1])
            s ^= 1 << num
        elif com[0] == "all":
            s = (1 << 21) - 1
        else:
            s = 0


if __name__ == "__main__":
    main()
