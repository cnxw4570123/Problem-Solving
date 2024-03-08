import sys

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline

V = int(input().rstrip())
E = int(input().rstrip())

graph = [[] for _ in range(V + 1)]
rev_graph = [[] for _ in range(V + 1)]


def main():
    for _ in range(E):
        a, b = map(int, input().split())
        graph[a].append(b)
        rev_graph[b].append(a)

    for i in range(1, V + 1):
        org = DFS(i, graph)
        rev = DFS(i, rev_graph)
        print(V - (org + rev - 1))


def DFS(cur, g):
    s = [cur]
    v = set()

    while s:
        now = s.pop()
        if now in v:
            continue
        v.add(now)

        for next in g[now]:
            if next in v:
                continue
            s.append(next)
    return len(v)


if __name__ == "__main__":
    main()
