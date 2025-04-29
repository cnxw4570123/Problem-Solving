ans, v = 0, []
def solution(k, dungeons):
    global v
    v = [False] * len(dungeons)
    find(k, dungeons, 0)
    return ans


def find(fatigue, dungeons, steps):
    global v, ans
    ans = max(ans, steps)
    for i in range(len(dungeons)):
        if v[i]:
            continue
        required, cost = dungeons[i]
        
        if fatigue < required:
            continue
            
        v[i] = True
        find(fatigue - cost, dungeons, steps + 1)
        v[i] = False