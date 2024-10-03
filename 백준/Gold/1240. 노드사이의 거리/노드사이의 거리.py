import sys
from heapq import heappop, heappush

# print = sys.stdout.write
input = sys.stdin.readline

INF = float("inf")
N, M = map(int, input().split())


_map = [[] for _ in range(N + 1)]

for _ in range(N - 1):
    a, b, d = map(int, input().split())
    _map[a].append((b, d))
    _map[b].append((a, d))


def dijkstra(a, b):
    dist = [INF] * (N + 1)
    dist[a] = 0

    q = [(a, 0)]

    while q:
        to, cost = heappop(q)

        if dist[to] < cost:
            continue

        for next, c in _map[to]:
            next_cost = cost + c
            if dist[next] <= next_cost:
                continue

            dist[next] = next_cost
            heappush(q, (next, next_cost))
    return dist[b]


def main():
    ans = []
    for _ in range(M):
        x, y = map(int, input().split())
        ans.append(dijkstra(x, y))

    print("\n".join(map(str, ans)))


if __name__ == "__main__":
    main()
