import sys


# print = sys.stdout.write
input = sys.stdin.readline

N, M = map(int, input().split())

parent = [i for i in range(N + 1)]


def find(x):
    if parent[x] == x:
        return x

    parent[x] = find(parent[x])
    return parent[x]


def union(x, y):
    x, y = find(x), find(y)

    if x == y:
        return

    if x > y:
        parent[x] = y
        return

    parent[y] = x


def main():
    for _ in range(M):
        u, v = map(int, input().split())
        union(u, v)
    ans = [find(i) for i in range(1, N + 1)]
    print(len(set(ans)))


if __name__ == "__main__":
    main()
