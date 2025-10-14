from heapq import heappush, heappop
import sys
MAX = sys.maxsize
dy, dx = (-1, 1, 0, 0), (0, 0, -1, 1)
direction = {0 : "H", 1 :"H", 2 : "V", 3 :"V"}

def solution(board):
    global N
    N = len(board)
    answer = find(board)
    return answer

def find(_map):
    q = [(0, 0, 0, 1), (0, 0, 0, 3)] # 수평, 수직
    costs = [[[MAX] * 4 for _ in range(N)] for _ in range(N)]
    costs[0][0] = [0, 0, 0, 0]
    
    while q:
        cost, y, x, dir = heappop(q)
        #print(f"cost = {cost}, y = {y}, x = {x}, dir = {direction[dir]}")
        for i in range(4):
            ny, nx = dy[i] + y, dx[i] + x
            if can_not_move(ny, nx, _map):
                continue
            ncost = cost + 100 + (0 if direction[dir] == direction[i] else 500)
            if ncost > costs[ny][nx][i]:
                continue
			
            heappush(q, (ncost, ny, nx, i))
            costs[ny][nx][i] = ncost
            #print(f"cost = {cost}, ny = {ny}, nx = {nx}, costs[{ny}][{nx}][{i}] = {costs[ny][nx][i]}")
    
    return min(costs[N - 1][N - 1])

def can_not_move(y, x, arr):
    return y >= N or x >= N or y < 0 or x < 0 or arr[y][x] == 1
    