import sys
from itertools import combinations

# print = sys.stdout.write
input = sys.stdin.readline

numbers = []

while (lotto := input().rstrip()) != "0":
    numbers.append(list(map(int, lotto.split()[1:])))


def main():
    ans = []
    for number in numbers:
        for comb in combinations(number, 6):
            ans.append(" ".join(map(str, comb)))
        ans.append("")
    print("\n".join(map(str, ans)))


if __name__ == "__main__":
    main()
