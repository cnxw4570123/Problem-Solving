import sys
from collections import Counter, deque

# print = sys.stdout.write
input = sys.stdin.readline


def main():
    global alphabet_cnts, q
    init()
    alphabet_cnts, q = Counter(), deque()

    start, ans = 0, 0
    for idx in range(len(strings)):
        if q and q[-1][0] == strings[idx]:
            q[-1][1] = idx
            alphabet_cnts[strings[idx]] += 1
            ans = max(ans, idx - start + 1)
            continue

        q.append([strings[idx], idx])
        alphabet_cnts[strings[idx]] += 1

        while q and len(alphabet_cnts) > N:
            alphabet, end_idx = q.popleft()

            cnt = end_idx - start + 1
            start = end_idx + 1
            if alphabet_cnts[alphabet] == cnt:
                del alphabet_cnts[alphabet]
                continue

            alphabet_cnts[alphabet] -= cnt
        ans = max(ans, idx - start + 1)

    print(ans)


def init():
    global N, strings
    N = int(input().rstrip())
    strings = input().rstrip()


if __name__ == "__main__":
    main()
