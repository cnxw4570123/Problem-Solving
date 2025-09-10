import sys
from collections import defaultdict

# print = sys.stdout.write
input = sys.stdin.readline

sushi = defaultdict(int)

N, D, K, C = map(int, input().split())
belt = [int(input().rstrip()) for _ in range(N)]
sushi[C] += 1
for i in range(K):
    sushi[belt[i]] += 1


def main():
    idx, ans = 0, len(sushi)
    while idx < len(belt):
        prev = belt[idx]
        sushi[prev] -= 1
        if sushi[prev] == 0:
            sushi.pop(prev)

        idx += 1
        sushi[belt[(idx + K - 1) % len(belt)]] += 1

        ans = max(ans, len(sushi))
    print(ans)


if __name__ == "__main__":
    main()
