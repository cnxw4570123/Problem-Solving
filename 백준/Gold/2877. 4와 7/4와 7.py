import sys

# print = sys.stdout.write
input = sys.stdin.readline

N = int(input().rstrip())


def main():
    digit = get_digit()

    move = N
    for i in range(1, digit, 1):
        move -= 1 << i
    ans = ["4"] * digit
    for i in range(digit):
        compare = 1 << (digit - (i + 1))
        if move > compare:
            move -= compare
            ans[i] = "7"

    print("".join(ans))


def get_digit():
    digit, sum = 1, 1

    while True:
        sum += 1 << digit
        digit += 1
        if N < sum:
            break

    return digit - 1


if __name__ == "__main__":
    main()
