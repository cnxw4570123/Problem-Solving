import sys


# print = sys.stdout.write
input = sys.stdin.readline

N, M = map(int, input().split())
sys.setrecursionlimit(10000)

graph = [[] for _ in range(N + 1)]
visited = [False] * (N + 1)
for _ in range(M):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)


def DFS(u):
    global visited
    if visited[u]:
        return

    visited[u] = True
    for next in graph[u]:
        DFS(next)


def main():
    ans = 0
    for i in range(1, N + 1):
        if visited[i]:
            continue
        DFS(i)
        ans += 1

    print(ans)


if __name__ == "__main__":
    main()
