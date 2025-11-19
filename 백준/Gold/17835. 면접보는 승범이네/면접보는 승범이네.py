import sys
from heapq import heappush, heappop

# print = sys.stdout.write
input = sys.stdin.readline
INF = float("inf")


def main():
    init()
    dist = dijkstra()
    ans = [0, 0]
    for i in range(1, N + 1):
        if dist[i] <= ans[1]:
            continue
        ans[0], ans[1] = i, dist[i]

    print(ans[0])
    print(ans[1])


def dijkstra():
    hq = []
    dist = [INF] * (N + 1)
    for place in interview_places:
        dist[place] = 0
        heappush(hq, (0, place))

    while hq:
        cost, current = heappop(hq)

        if dist[current] < cost:
            continue

        for next_node, next_cost in graph[current]:
            new_cost = next_cost + cost

            if dist[next_node] <= new_cost:
                continue
            dist[next_node] = new_cost
            heappush(hq, (new_cost, next_node))

    return dist


def init():
    global N, M, K, graph, interview_places
    N, M, K = map(int, input().split())

    graph = [[] for _ in range(N + 1)]

    for _ in range(M):
        u, v, c = map(int, input().split())
        graph[v].append((u, c))

    interview_places = list(map(int, input().split()))


if __name__ == "__main__":
    main()
