import sys

# print = sys.stdout.write
input = sys.stdin.readline


N, L = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(N)]


def main():
    ans = 0
    for i in range(N):
        ans += check(arr[i])
        ans += check([arr[j][i] for j in range(N)])

    print(ans)


def check(heights):
    bridge = [False] * N

    for i in range(N - 1):
        if heights[i] == heights[i + 1]:
            continue

        # 경사로 설치 불가면 바로 실패
        if abs(heights[i] - heights[i + 1]) != 1:
            return 0

        # 오른쪽으로 경사로 설치
        if heights[i] > heights[i + 1]:
            if not is_settalble(i + 1, i + L + 1, heights, bridge):
                return 0
            continue

        # 왼쪽으로 경사로 설치
        if not is_settalble(i, i - L, heights, bridge, -1):
            return 0

    return 1


def is_settalble(start, end, heights, bridge, step=1):
    measure = heights[start]
    for idx in range(start, end, step):
        # 범위 벗어나면
        if 0 > idx or idx >= N:
            return False

        if heights[idx] != measure:
            return False
        # 겹쳐서 놓을 수 없음
        if bridge[idx]:
            return False
        bridge[idx] = True
    return True


if __name__ == "__main__":
    main()
