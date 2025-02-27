import sys

# print = sys.stdout.write
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N)]
visited = [False] * N
root = [True] * N
still_get_drugs = [False] * N


def main():
    for _ in range(M):
        _from, _to = map(lambda x: ord(x) - 65, input().split())
        root[_to] = False  # 가장 상위 공급책만 남김
        graph[_from].append(_to)

    for people in input().split()[1:]:
        visited[ord(people) - 65] = True

    for idx in range(N):
        if not root[idx] or visited[idx]:
            continue
        visited[idx] = True
        DFS(idx)

    print(still_get_drugs[1:].count(True))


def DFS(people):
    for next in graph[people]:
        if visited[next]:
            continue
        visited[next] = True
        still_get_drugs[next] = True
        DFS(next)


if __name__ == "__main__":
    main()
