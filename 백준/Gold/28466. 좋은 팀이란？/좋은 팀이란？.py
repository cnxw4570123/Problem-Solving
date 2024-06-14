import sys
from collections import defaultdict
from heapq import heappop, heappush
from itertools import combinations

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
MAX_IDX1, MAX_IDX2 = 10, 12

people = defaultdict(lambda: [])


N = int(input().rstrip())
cheongan = [list(map(int, input().split())) for _ in range(MAX_IDX1)]
jiji = [list(map(int, input().split())) for _ in range(MAX_IDX2)]


def calc(x, y, z):
    x_idx, y_idx, z_idx = segregate(x), segregate(y), segregate(z)
    xy = cheongan[x_idx[0]][y_idx[0]] + jiji[x_idx[1]][y_idx[1]]
    yz = cheongan[y_idx[0]][z_idx[0]] + jiji[y_idx[1]][z_idx[1]]
    zx = cheongan[z_idx[0]][x_idx[0]] + jiji[z_idx[1]][x_idx[1]]
    return xy + yz + zx + x[1] + y[1] + z[1]


def segregate(saju):
    # 사주의 각 글자를 인덱스로 바꿔줌
    return int(saju[0][0]), ord(saju[0][1]) - 65


three_by_categories = []


def main():
    for _ in range(N):
        performance, saju = input().split()
        performance = int(performance)
        heappush(people[saju], (-performance, performance))

    ans = 0
    for saju, scores in people.items():
        for _ in range(3):
            if not scores:
                break
            three_by_categories.append((saju, heappop(scores)[1]))

    for x, y, z in combinations(three_by_categories, 3):
        ans = max(calc(x, y, z), ans)

    print(ans)


if __name__ == "__main__":
    main()
