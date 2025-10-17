import sys

# print = sys.stdout.write
input = sys.stdin.readline


def main():
    global v
    init()
    for i in range(1, N + 1):
        v[i] = True
        dp[i] = DFS(i)
        v = [False] * (N + 1)

    temp = sorted(zip(dp[1:], range(1, N + 1)), key=lambda arr: (-arr[0], arr[1]))
    ans = [temp[0][1]]

    for cnt, v in temp[1:]:
        if cnt == temp[0][0]:
            ans.append(v)
    print(" ".join(map(str, ans)))


def init():
    global N, M, graph, dp, v
    N, M = map(int, input().split())
    graph = [[] for _ in range(N + 1)]

    for _ in range(M):
        child, parent = map(int, input().split())
        graph[parent].append(child)

    dp = [0] * (N + 1)
    v = [False] * (N + 1)


def DFS(start):
    res = 1
    s = [start]
    while s:
        current = s.pop()
        for next in graph[current]:
            if v[next]:
                continue
            v[next] = True
            res += 1
            s.append(next)
    return res


if __name__ == "__main__":
    main()
