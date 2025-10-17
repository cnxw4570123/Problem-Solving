import sys
from collections import defaultdict

# print = sys.stdout.write
input = sys.stdin.readline


def main():
    init()
    numbers = defaultdict(int)
    for alphabet in alphabets:
        for i in range(len(alphabet)):
            numbers[alphabet[i]] += 10 ** (len(alphabet) - 1 - i)

    current, ans = 9, 0
    for _, value in sorted(numbers.items(), key=lambda item: (-item[1])):
        ans += current * value
        current -= 1
    print(ans)


def init():
    global N, alphabets
    N = int(input().rstrip())
    alphabets = [input().rstrip() for _ in range(N)]


if __name__ == "__main__":
    main()
