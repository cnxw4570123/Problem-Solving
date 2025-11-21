import sys
from heapq import heappush, heappop

# print = sys.stdout.write
input = sys.stdin.readline
INF = float("inf")


def main():
    init()
    dist = dijkstra(A)

    for u in range(1, N + 1):
        if dist[u] == INF:
            continue
        for v, w in graph[u]:
            if dist[u] + w == dist[v]:
                prev[v].add(u)

    ans = set()

    s = [B]
    while s:
        current = s.pop()

        ans.add(current)

        for next in prev[current]:
            s.append(next)

    print(len(ans))
    print(" ".join(map(str, sorted(ans))))


def dijkstra(start):
    global prev
    dist = [INF] * (N + 1)
    dist[start] = 0

    hq = [(0, start)]

    while hq:
        cost, current = heappop(hq)

        if dist[current] < cost:
            continue

        for next_city, next_cost in graph[current]:
            new_cost = next_cost + cost

            if dist[next_city] <= new_cost:
                continue
            dist[next_city] = new_cost
            heappush(hq, (new_cost, next_city))

    return dist


def init():
    global N, M, A, B, graph, prev
    N, M, A, B = map(int, input().split())
    graph = [[] for _ in range(N + 1)]
    prev = [set() for _ in range(N + 1)]

    for _ in range(M):
        a, b, c = map(int, input().split())

        graph[a].append((b, c))
        graph[b].append((a, c))


if __name__ == "__main__":
    main()
