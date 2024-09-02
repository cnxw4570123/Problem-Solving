import sys
from heapq import heappop, heappush

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
#f = open("input.txt", "rt")
#input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
T = int(input().rstrip())
INF = float("inf")


def dijkstra(graph, n, s, g, h):
    dist = [[INF, False] for _ in range(n + 1)]
    dist[s][0] = 0
    q = [(0, False, s)]

    while q:
        cost, contains, current = heappop(q)

        # 최단 거리가 아니거나 최단거리여도 이미 구간을 포함했으면
        if dist[current][0] < cost or (dist[current][0] == cost and dist[current][1]):
            continue
        dist[current][1] |= contains

        for value, next in graph[current]:
            next_cost, next_contains = (
                cost + value,
                ((current, next) in ((g, h), (h, g))) | contains,
            )
            # 비용이 더 크거나
            if dist[next][0] < next_cost:
                continue
            # 비용이 같더라도 교차로를 포함하지 않으면
            if dist[next][0] == next_cost and not next_contains:
                continue

            dist[next][0] = next_cost
            heappush(q, (next_cost, next_contains, next))
    return dist


def main():
    for _ in range(T):
        n, m, t = map(int, input().split())

        graph = [[] for _ in range(n + 1)]
        s, g, h = map(int, input().split())

        for _ in range(m):
            a, b, d = map(int, input().split())
            graph[a].append((d, b))
            graph[b].append((d, a))

        candidates = sorted([int(input().rstrip()) for _ in range(t)])

        dist = dijkstra(graph, n, s, g, h)
        ans = [num for num in candidates if dist[num][1] and dist[num][0] != INF]
        print(" ".join(map(str, ans)))


if __name__ == "__main__":
    main()
