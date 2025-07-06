import sys


# print = sys.stdout.write
input = sys.stdin.readline

permutations = []

for _ in range(2):
    _ = input()
    permutations.append(list(map(int, input().split())))


def main():
    common_elements = set(permutations[0]) & set(permutations[1])

    if not common_elements:
        print(0)
        return

    ans = []
    while common_elements:
        max_val = max(common_elements)
        ans.append(max_val)

        idx1 = permutations[0].index(max_val)
        idx2 = permutations[1].index(max_val)

        permutations[0] = permutations[0][idx1 + 1 :]
        permutations[1] = permutations[1][idx2 + 1 :]

        common_elements = set(permutations[0]) & set(permutations[1])

    print(len(ans))
    print(*ans)


if __name__ == "__main__":
    main()
