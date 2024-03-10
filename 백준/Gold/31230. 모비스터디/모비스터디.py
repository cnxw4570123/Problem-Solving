import sys
import heapq

input = sys.stdin.readline
INF = sys.maxsize

# 도시 개수, 도로 개수, 민겸 도시, 시은 도시
N, M, A, B = map(int, input().split())
edge_lst = [[] for _ in range(N + 1)]
for _ in range(M):
    a, b, c = map(int, input().split())
    edge_lst[a].append((b, c))
    edge_lst[b].append((a, c))


def dijkstra(start):
    dist = [INF for _ in range(N + 1)]
    # 초기 세팅
    hq = []
    heapq.heappush(hq, (0, start))
    dist[start] = 0

    while hq:
        now_dist, now = heapq.heappop(hq)
        if now_dist > dist[now]:
            continue
        for next, next_dist in edge_lst[now]:
            if now_dist + next_dist < dist[next]:
                dist[next] = now_dist + next_dist
                heapq.heappush(hq, (dist[next], next))

    return dist


dist_a = dijkstra(A)
dist_b = dijkstra(B)

ans = []

for i in range(1, N + 1):
    if dist_a[i] == INF or dist_b[i] == INF:
        continue
    if dist_a[i] + dist_b[i] == dist_a[B]:
        ans.append(i)

print(len(ans))
print(*ans)
