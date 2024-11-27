import sys

# print = sys.stdout.write
input = sys.stdin.readline
sys.setrecursionlimit(10**4)

DIRECTIONS = (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)

R, C = map(int, input().split())
size = [list(map(int, input().split())) for _ in range(R)]

parents = [[(i, j) for j in range(C)] for i in range(R)]


def find(y, x):
    if parents[y][x] == (y, x):
        return y, x

    parents[y][x] = find(*parents[y][x])
    return parents[y][x]


def union(cell1, cell2):
    cell1, cell2 = find(*cell1), find(*cell2)

    if cell1 == cell2:
        return

    if size[cell1[0]][cell1[1]] >= size[cell2[0]][cell2[1]]:
        parents[cell1[0]][cell1[1]] = cell2
        return

    parents[cell2[0]][cell2[1]] = cell1


def main():
    for i in range(R):
        for j in range(C):
            ey, ex, target = i, j, size[i][j]
            for dy, dx in DIRECTIONS:
                ny, nx = i + dy, j + dx
                if ny >= R or nx >= C or ny < 0 or nx < 0 or target < size[ny][nx]:
                    continue
                ey, ex, target = ny, nx, size[ny][nx]
            union((i, j), (ey, ex))

    ans = [[0] * C for _ in range(R)]
    for i in range(R):
        for j in range(C):
            y, x = find(*parents[i][j])
            ans[y][x] += 1

    for row in ans:
        print(*row)


if __name__ == "__main__":
    main()
