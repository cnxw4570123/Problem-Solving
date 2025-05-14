map, pool = [], set()
MOD = 1_000_000_007
DIRECTIONS = (-1, 0), (0, -1)
def solution(m, n, puddles):
    global map, pool
    
    map = [[-1] * m for _ in range(n)]
    pool = {(r - 1, c - 1) for c, r in puddles}
    

    answer = find(n - 1, m - 1)
    return answer

def find(y, x):
    global map
    if (y, x) == (0, 0):
        return 1
    
    if map[y][x] != -1:
        return map[y][x]
    
    map[y][x] = 0
    for dy, dx in DIRECTIONS:
        ny, nx = dy + y, dx + x
        if ny >= len(map) or nx >= len(map[0]) or ny < 0 or nx < 0 or (ny, nx) in pool:
            continue
        
        map[y][x] = (map[y][x] + find(ny, nx)) % MOD
        
    return map[y][x]
       	