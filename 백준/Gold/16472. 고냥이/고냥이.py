import sys
from collections import Counter

# print = sys.stdout.write
input = sys.stdin.readline

def main():
    global alphabet_cnts
    init()
    alphabet_cnts = Counter()

    left, ans = 0, 0

    for right in range(len(strings)):
        alphabet_cnts[strings[right]] += 1

        while len(alphabet_cnts) > N:
            if alphabet_cnts[strings[left]] == 1:
                del alphabet_cnts[strings[left]]
            else:
                alphabet_cnts[strings[left]] -= 1

            left += 1
        ans = max(ans, right - left + 1)
    print(ans)


def init():
    global N, strings
    N = int(input().rstrip())
    strings = input().rstrip()


if __name__ == "__main__":
    main()
