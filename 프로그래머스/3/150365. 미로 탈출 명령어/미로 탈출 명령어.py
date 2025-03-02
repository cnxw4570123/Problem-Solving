import sys


sys.setrecursionlimit(10**6)
dy, dx = (1, 0, 0, -1), (0, -1, 1, 0) # 방향 사전순
to_alpha = 'dlru'
N, M, R, C, K, v = (-1, -1, -1, -1, -1, -1)
def solution(n, m, x, y, r, c, k):
    global N, M, R, C, K, v
    v = [[[''] * (m + 1) for _ in range(n + 1)] for _ in range(k + 1)]
    N,M,R,C,K = n, m, r, c, k
    if (abs(x - r) + abs(y - c) + k ) % 2 != 0:
    	return 'impossible'
    return DFS(x, y, 0)

def DFS(y, x, t):
    if (y, x, t) == (R, C, K):
        return v[t][y][x]
    
    for idx in range(4):
        ny, nx = y + dy[idx], x + dx[idx]
        if ny > N or nx > M or ny < 1 or nx < 1 or t + 1 > K or v[t + 1][ny][nx]:
            continue
        v[t+1][ny][nx] = v[t][y][x] + to_alpha[idx]
        check = DFS(ny, nx, t + 1)
        if check != 'impossible':
            return check
        
    return 'impossible'
           
    