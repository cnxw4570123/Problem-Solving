import sys
import heapq

INF = sys.maxsize
# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline

N, M = map(int, input().split())
cost = [-INF] * (N + 1)
graph = [[] for _ in range(N + 1)]
for _ in range(M):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))


start, end = map(int, input().split())


def main():
    dijkstra()
    print(cost[end])


def dijkstra():
    q = [(-INF, INF, start)]
    cost[start] = 0
    while q:
        prior, constraint, cur = heapq.heappop(q)
        if constraint < cost[cur]:
            continue

        for next, new_constraint in graph[cur]:
            next_constraint = min(new_constraint, constraint)
            if cost[next] >= next_constraint:
                continue
            cost[next] = next_constraint
            heapq.heappush(q, (-next_constraint, next_constraint, next))


if __name__ == "__main__":
    main()
