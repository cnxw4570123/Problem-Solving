import sys
from collections import defaultdict

# print = sys.stdout.write
input = sys.stdin.readline

CORRECTION = 0.00001
forest = defaultdict(int)
total_cnt = 0


def main():
    global total_cnt
    while tree := input().rstrip():
        total_cnt += 1
        forest[tree] += 1

    for tree_name, cnt in sorted(forest.items(), key=lambda x: x[0]):
        print(f"{tree_name} {round((cnt/total_cnt) * 100 + CORRECTION , 4) :.4f}")


if __name__ == "__main__":
    main()
