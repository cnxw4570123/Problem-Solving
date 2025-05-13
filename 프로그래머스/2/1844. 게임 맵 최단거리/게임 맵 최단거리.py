from collections import deque


DIRECTIONS = (-1, 0), (1, 0), (0, -1), (0, 1)
r, c = 0, 0

def solution(maps):
    global r, c
    r, c = len(maps), len(maps[0])    
    answer = BFS(maps)

    
    return answer


def BFS(maps):
    q = deque([(0, 0, 0)])
    v = [[False] * c for _ in range(r)]
    
    while q:
        y, x, cnt = q.popleft()
        
        if (y,x) == (r - 1, c - 1):
            return cnt + 1
        
        for dy, dx in DIRECTIONS:
            ny, nx = dy + y, dx + x
            
            if ny >= r or nx >= c or ny < 0 or nx < 0 or v[ny][nx] or maps[ny][nx] == 0:
                continue
            
            v[ny][nx] = True
            q.append((ny, nx, cnt + 1))
    return -1