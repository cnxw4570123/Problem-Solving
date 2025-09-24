import sys
from itertools import permutations
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

N = int(input().rstrip())
tries = [tuple(map(int, input().split())) for _ in range(N)]


def main():
    calculate()


def calculate():
    candiadates = deque(permutations(range(1, 10), 3))
    ans = set()
    for candidate in candiadates:
        is_valid = True

        for number, strike, ball in tries:
            current = str(number)

            strike_cnt = ball_cnt = 0
            for i in range(3):
                if str(candidate[i]) == current[i]:
                    strike_cnt += 1
                    continue
                if str(candidate[i]) in current:
                    ball_cnt += 1

            if strike_cnt != strike or ball_cnt != ball:
                is_valid = False
                break

        if is_valid:
            ans.add(candidate)

    print(len(ans))


if __name__ == "__main__":
    main()
