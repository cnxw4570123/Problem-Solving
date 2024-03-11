import sys
import math

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline


class SegmentTree:
    def __init__(self, n) -> None:
        tree_height = math.ceil(math.log2(n)) + 1
        count = round(2**tree_height)
        self.tree = [0] * count

    def sum(self, node, start, end, left, right):
        if left > end or right < start:
            return 0

        if left <= start and end <= right:
            return self.tree[node]
        mid = (start + end) >> 1
        return self.sum(node * 2, start, mid, left, right) + self.sum(
            node * 2 + 1, mid + 1, end, left, right
        )

    def update(self, node, start, end, idx, diff):
        # 더한 값으로 수정하면서 반환하기 때문에 범위에서 벗어나도 가져오긴해야함.
        if idx > end or idx < start:
            return self.tree[node]
        if start == end:
            self.tree[node] += diff
            return self.tree[node]

        mid = (start + end) >> 1
        self.tree[node] = self.update(node * 2, start, mid, idx, diff) + self.update(
            node * 2 + 1, mid + 1, end, idx, diff
        )
        return self.tree[node]


N, M = map(int, input().split())
arr = [0] * (N + 1)


def main():
    stree = SegmentTree(N)
    for _ in range(M):
        com, a, b = map(int, input().split())
        if com == 0:
            if a > b:
                temp = a
                a = b
                b = temp

            print(stree.sum(1, 1, N, a, b))
        else:
            stree.update(1, 1, N, a, b - arr[a])
            arr[a] = b


if __name__ == "__main__":
    main()
