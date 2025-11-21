import sys
from heapq import heappush, heappop

# print = sys.stdout.write
input = sys.stdin.readline
INF = float("inf")


def main():
    init()
    dist_a, dist_b = dijkstra(A), dijkstra(B)
    ans = []

    for i in range(1, N + 1):
        if dist_a[i] + dist_b[i] == dist_a[B]:
            ans.append(i)

    print(len(ans))
    print(" ".join(map(str, ans)))


def dijkstra(start):
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
    global N, M, A, B, graph
    N, M, A, B = map(int, input().split())
    graph = [[] for _ in range(N + 1)]

    for _ in range(M):
        a, b, c = map(int, input().split())

        graph[a].append((b, c))
        graph[b].append((a, c))


if __name__ == "__main__":
    main()
