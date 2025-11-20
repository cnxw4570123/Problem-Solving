from heapq import heappop, heappush

INF = float("inf")


def solution(n, s, a, b, fares):
    global graph, N
    N = n
    graph = [[] for _ in range(n + 1)]
    for start, end, cost in fares:
        graph[start].append((end, cost))
        graph[end].append((start, cost))

    dist_start = dijkstra(s)
    dist_a, dist_b = dijkstra(a), dijkstra(b)

    answer = dist_start[a] + dist_start[b]
    for i in range(1, n + 1):
        candidate = dist_a[i] + dist_b[i] + dist_start[i]
        if candidate < answer:
            answer = candidate
    return answer


def dijkstra(s):
    dist = [INF] * (N + 1)
    dist[s] = 0

    hq = [(0, s)]

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
