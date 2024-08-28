import sys
from collections import deque
from itertools import combinations

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
INF = sys.maxsize

N = int(input().rstrip())
population = [0] + list(map(int, input().split()))
graph = [[]] + [list(map(int, input().split()[1:])) for _ in range(N)]


def BFS(arr):
    q, v = deque([arr[0]]), set([arr[0]])
    res = population[arr[0]]
    while q:
        cur = q.popleft()

        for next in graph[cur]:
            if next in v or next not in arr:
                continue
            # 인접한 구역 중 방문하지 않았고, 선택한 조합 내의 원소면
            q.append(next)
            v.add(next)
            res += population[next]

    return len(v), res


def main():
    _min = INF

    for i in range(1, N // 2 + 1):
        for comb in combinations(range(1, N + 1), i):  # i개의 조합 생성
            v1, sum1 = BFS(comb)
            v2, sum2 = BFS([i for i in range(1, N + 1) if i not in comb])

            if v1 + v2 != N:
                continue

            _min = min(_min, abs(sum1 - sum2))

    print(_min if _min != INF else -1)


if __name__ == "__main__":
    main()
