import sys
import heapq

input = sys.stdin.readline
INF = float("inf")

N = int(input().rstrip())
board = [list(input().rstrip()) for _ in range(N)]
dist = [[INF] * N for _ in range(N)]


def dijkstra():
    q = []
    heapq.heappush(q, (0, 0, 0))  # 벽 부순 횟수 좌표
    dist[0][0] = 0
    while q:
        cnt, y, x = heapq.heappop(q)
        if dist[y][x] < cnt:
            continue

        for dy, dx in (-1, 0), (1, 0), (0, -1), (0, 1):
            ny, nx = dy + y, dx + x
            if ny >= N or nx >= N or ny < 0 or nx < 0 or dist[ny][nx] <= cnt:
                continue
            if board[ny][nx] == "1":
                heapq.heappush(q, (cnt, ny, nx))
                dist[ny][nx] = cnt
                continue

            if board[ny][nx] == "0" and dist[ny][nx] > cnt + 1:
                heapq.heappush(q, (cnt + 1, ny, nx))
                dist[ny][nx] = cnt + 1


def main():
    dijkstra()
    print(dist[N - 1][N - 1])


if __name__ == "__main__":
    main()
