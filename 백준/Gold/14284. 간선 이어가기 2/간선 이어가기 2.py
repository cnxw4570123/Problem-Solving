import sys
from heapq import heappop, heappush

# print = sys.stdout.write
input = sys.stdin.readline


INF = float("inf")


N, M = map(int, input().split())
graph = [[] for _ in range(N + 1)]

for _ in range(M):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

S, T = map(int, input().split())


def main():
    print(dijkstra())


def dijkstra():
    dist = [INF] * (N + 1)
    dist[S] = 0

    pq = [(0, S)]
    while pq:
        current_cost, current = heappop(pq)
        if current == T:
            return dist[T]

        for next, next_cost in graph[current]:
            total = current_cost + next_cost
            if total >= dist[next]:
                continue

            dist[next] = total
            heappush(pq, (total, next))


if __name__ == "__main__":
    main()
