import sys

sys.setrecursionlimit(10**5)

# print = sys.stdout.write
input = sys.stdin.readline


N = int(input().strip())
graph = [[] for _ in range(N + 1)]
v = [False] * (N + 1)

for _ in range(N - 1):
    p, c, w = map(int, input().split())
    graph[p].append((c, w))
    graph[c].append((p, w))


def main():
    global v
    v[1] = True
    _, far_node = DFS(1)
    v = [False] * (N + 1)

    v[far_node] = True
    ans, _ = DFS(far_node)
    print(ans)


# 반환해야 하는 것 => 노드랑 거리
def DFS(cur):
    dist = [0, cur]
    candidate = [0, cur]
    for child, weight in graph[cur]:
        if v[child]:
            continue

        v[child] = True
        result = DFS(child)

        if candidate[0] <= result[0] + weight:
            candidate = [result[0] + weight, result[1]]

    if dist[0] <= candidate[0]:
        dist = candidate

    return dist


if __name__ == "__main__":
    main()
