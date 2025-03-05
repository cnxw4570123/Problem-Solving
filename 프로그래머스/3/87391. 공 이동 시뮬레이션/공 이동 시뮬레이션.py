# DIRECTIONS = (0, 1), (0, -1), (1, 0), (-1, 0) # 쿼리의 반대방향

def solution(n, m, x, y, queries):
    y1, y2, x1, x2 = x, x, y, y # y최소, y최대, x최소, x최대
    for dir, dist in queries[::-1]:
        if check(y1, y2, x1, x2, n, m):
            return 0
        
        # 오른쪽으로 이동
        if dir == 0:
            x2 = min(x2 + dist, m - 1)
            if x1 != 0:
                x1 += dist
            continue
            
        # 왼쪽으로 이동
        if dir == 1:
            x1 = max(0, x1 - dist)
            if x2 != m - 1:
                x2 -= dist
            continue
        
        # 아래쪽으로 이동
        if dir == 2:
            y2 = min(y2 + dist, n - 1)
            if y1 != 0:
                y1 += dist
            continue
        
        y1 = max(0, y1 - dist)
        if y2 != n - 1:
            y2 -= dist
            
    else:
        if check(y1, y2, x1, x2, n, m):
            return 0
    
    return (y2 - y1 + 1) * (x2 - x1 + 1)

def check(y1, y2, x1, x2, n, m):
    return y1 >= n or x1 >= m or y2 < 0 or x2 < 0
        
            

