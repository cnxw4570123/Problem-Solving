import sys
from heapq import heappush, heappop

# print = sys.stdout.write
input = sys.stdin.readline

INF = sys.maxsize


def main():
    init()

    dist, rev_dist = dijkstra(X, graph), dijkstra(X, reverse_graph)

    ans = max([dist[i] + rev_dist[i] for i in range(1, N + 1)])
    print(ans)


def dijkstra(node, g):
    res = [INF] * (N + 1)

    hq = [(0, node)]

    res[node] = 0

    while hq:
        cost, current = heappop(hq)

        if res[current] < cost:
            continue

        for next_node, next_cost in g[current]:
            new_cost = next_cost + cost

            if res[next_node] <= new_cost:
                continue

            res[next_node] = new_cost
            heappush(hq, (new_cost, next_node))
    return res


def init():
    global N, M, X, graph, reverse_graph
    N, M, X = map(int, input().split())
    graph = [[] for _ in range(N + 1)]
    reverse_graph = [[] for _ in range(N + 1)]
    for _ in range(M):
        _from, to, cost = map(int, input().split())

        graph[_from].append((to, cost))
        reverse_graph[to].append((_from, cost))


if __name__ == "__main__":
    main()
