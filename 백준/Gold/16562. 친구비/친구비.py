import sys

input = sys.stdin.readline
# print = sys.stdout.write


def find(x):
    if parent[x] == x:
        return x
    parent[x] = find(parent[x])
    return parent[x]


def union(x, y):
    x = find(x)
    y = find(y)

    if x == y:
        return

    if cost[x] < cost[y]:
        parent[y] = x
    else:
        parent[x] = y


INF = float("inf")
N, M, K = map(int, input().rstrip().split())
parent = [i for i in range(N + 1)]
v = [False for _ in range(N + 1)]
cost = list(map(int, input().rstrip().split()))
cost.insert(0, INF)

for _ in range(M):
    a, b = map(int, input().rstrip().split())
    union(a, b)

sum = 0
for friend in set(parent[1:]):
    friend = find(friend)
    if v[friend]:
        continue
    sum += cost[friend]
    v[friend] = True

if sum > K:
    print("Oh no")
else:
    print(sum)
