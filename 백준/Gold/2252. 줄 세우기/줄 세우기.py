import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N + 1)]
in_degree = [0] * (N + 1)

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    in_degree[b] += 1


def main():
    topology_sort()


def topology_sort():
    q = deque([i for i in range(1, N + 1) if not in_degree[i]])

    while q:
        current = q.popleft()
        print(current)

        for next in graph[current]:
            in_degree[next] -= 1

            if not in_degree[next]:
                q.append(next)


if __name__ == "__main__":
    main()
