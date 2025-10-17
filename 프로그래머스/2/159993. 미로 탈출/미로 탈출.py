from collections import deque

DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)

def solution(maps):    
    start = init(maps)
    answer = BFS(start)
    return answer

def BFS(start):
    v = [[[False] * M for _ in range(N)] for _ in range(2)]
    v[0][start[0]][start[1]] = True
    
    q = deque([(*start, False, 0)])
    while q:
        y, x, lever, t = q.popleft()
        
        if lever and board[y][x] == 'E':
            return t
        
        for dy, dx in DIRECTIONS:
            ny, nx = dy + y, dx + x
            if ny >= N or nx >= M or ny < 0 or nx < 0 or v[lever][ny][nx] or board[ny][nx] == 'X':
                continue
            
            if board[ny][nx] == 'L':
                q.append((ny, nx, True, t + 1))
                v[lever][ny][nx] = True
                continue
            q.append((ny, nx, lever, t + 1))
            v[lever][ny][nx] = True
    
    return -1

def init(maps):
    global board, N, M
    board, N, M = maps, len(maps), len(maps[0])
    
    for i in range(N):
        for j in range(M):
            if board[i][j] == 'S':
                return i, j

    