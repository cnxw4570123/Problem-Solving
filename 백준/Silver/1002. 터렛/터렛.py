import sys
from math import sqrt

# print = sys.stdout.write
input = sys.stdin.readline

N = int(input().rstrip())


def main():
    for _ in range(N):
        x1, y1, r1, x2, y2, r2 = map(int, input().split())

        dist = sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)  # 두 원점 사이의 거리

        if dist == 0 and r1 == r2:  # 두 원이 같을 경우
            print(-1)
            continue

        # 내접 또는 외접
        if abs(r1 - r2) == dist or dist == r1 + r2:
            print(1)
            continue

        if abs(r1 - r2) < dist < r1 + r2:  # 두 원이 서로 다른 두 점에서 만날 경우
            print(2)
            continue

        print(0)


if __name__ == "__main__":
    main()
