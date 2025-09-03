import sys
from itertools import combinations

# print = sys.stdout.write
input = sys.stdin.readline


N, K = map(int, input().split())
positions = [tuple(map(int, input().split())) for _ in range(N)]


def main():
    ans = sys.maxsize
    for shelters in combinations(positions, K):
        # 대피소 먼저 할당
        # 각 좌표마다 가까운 대피소 거리 찾기
        dists = []
        for pos in positions:
            if pos in shelters:
                continue
            closest_dist = min([calc(pos, shelter) for shelter in shelters])
            dists.append(closest_dist)
        # 거리 중 가장 먼 거리 찾기
        ans = min(ans, max(dists))

    print(ans)


def calc(pos: tuple, shelter: tuple):
    return abs(pos[0] - shelter[0]) + abs(pos[1] - shelter[1])


if __name__ == "__main__":
    main()
