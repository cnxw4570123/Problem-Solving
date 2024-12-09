import sys

# print = sys.stdout.write
input = sys.stdin.readline

sys.setrecursionlimit(10**7)
EARLY_ADAPTER, FOLLOWER = 1, 0
N = int(input().rstrip())
graph = [[] for _ in range(N + 1)]
dp = [[0, 0] for _ in range(N + 1)]
visited = [False] * (N + 1)

for _ in range(N - 1):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)


def DFS(number):
    visited[number] = True

    dp[number][EARLY_ADAPTER] = 1
    dp[number][FOLLOWER] = 0

    for next in graph[number]:
        if visited[next]:
            continue
        DFS(next)
        dp[number][FOLLOWER] += dp[next][EARLY_ADAPTER]
        dp[number][EARLY_ADAPTER] += min(dp[next][FOLLOWER], dp[next][EARLY_ADAPTER])


def main():
    DFS(1)
    print(min(dp[1]))


if __name__ == "__main__":
    main()
