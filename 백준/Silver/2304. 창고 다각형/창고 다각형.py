import sys

# print = sys.stdout.write
input = sys.stdin.readline


N = int(input().rstrip())

blocks = []
h, h_idx = 0, -1
blocks = sorted([tuple(map(int, input().split())) for _ in range(N)])

h_idx, h = 0, 0
for i in range(N):
    if h < blocks[i][1]:
        h_idx, h = i, blocks[i][1]


def main():
    ans = 0
    x, y = blocks[0]
    for i in range(h_idx + 1):
        block = blocks[i]
        if y <= block[1]:
            ans += y * (block[0] - x)
            x, y = block

    x, y = blocks[-1]
    for i in range(N - 1, h_idx - 1, -1):
        block = blocks[i]
        if y <= block[1]:
            ans += y * (x - block[0])
            x, y = block

    print(ans + h)


if __name__ == "__main__":
    main()
