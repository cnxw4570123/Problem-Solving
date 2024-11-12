import sys
from heapq import heappop, heappush

# print = sys.stdout.write
input = sys.stdin.readline


INF = float("inf")


N, M = map(int, input().split())
graph = [[INF] * (N + 1) for _ in range(N + 1)]
for i in range(1, N + 1):
    graph[i][i] = 0

for _ in range(M):
    a, b, c = map(int, input().split())
    graph[a][b] = min(graph[a][b], c)
    graph[b][a] = min(graph[b][a], c)

S, T = map(int, input().split())


def main():
    print(dijkstra())


def dijkstra():
    dist = [INF] * (N + 1)
    dist[S] = 0

    pq = [(0, S)]
    while pq:
        cost, to = heappop(pq)
        if to == T:
            return dist[T]

        for next in range(1, N + 1):
            if next == to or graph[to][next] == INF:
                continue

            next_cost = cost + graph[to][next]
            if next_cost >= dist[next]:
                continue
            dist[next] = next_cost
            heappush(pq, (next_cost, next))


if __name__ == "__main__":
    main()
