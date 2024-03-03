import sys
import bisect

input = sys.stdin.readline
# print = sys.stdout.write


def main():
    # f = open("input.txt", "rt")
    # input = f.readline

    N = int(input().rstrip())
    crains = sorted(list(map(int, input().rstrip().split())))
    M = int(input().rstrip())
    boxes = sorted(list(map(int, input().rstrip().split())))
    ans = 0
    while boxes:
        ans += 1
        min_idx = bisect.bisect_left(crains, boxes[0])
        crains = crains[min_idx:]
        if not crains:
            print(-1)
            return

        for weight in crains:
            idx = bisect.bisect_right(boxes, weight)
            if not boxes or boxes[idx - 1] > weight:
                continue
            boxes.pop(idx - 1)
    print(ans)


if __name__ == "__main__":
    main()
