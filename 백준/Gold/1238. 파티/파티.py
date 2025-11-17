import sys
from heapq import heappush, heappop

# print = sys.stdout.write
input = sys.stdin.readline

INF = sys.maxsize


def main():
    init()

    for i in range(1, N + 1):
        dijkstra(i)

    ans = 0
    for i in range(1, N + 1):
        ans = max(ans, dist[i][X] + dist[X][i])

    print(ans)


def dijkstra(node):
    global dist

    hq = [(0, node)]

    dist[node][node] = 0

    while hq:
        cost, current = heappop(hq)

        if dist[node][current] < cost:
            continue

        for next_node, next_cost in graph[current]:
            new_cost = next_cost + cost

            if dist[node][next_node] <= new_cost:
                continue

            dist[node][next_node] = new_cost
            heappush(hq, (new_cost, next_node))


def init():
    global N, M, X, graph, dist
    N, M, X = map(int, input().split())
    graph = [[] for _ in range(N + 1)]

    for _ in range(M):
        _from, to, cost = map(int, input().split())

        graph[_from].append((to, cost))

    dist = [[INF] * (N + 1) for _ in range(N + 1)]


if __name__ == "__main__":
    main()
