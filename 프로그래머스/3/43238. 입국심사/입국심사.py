def solution(n, times):
    times = sorted(times)
    lo, hi = 0, times[-1] * n + 1
    while lo + 1 < hi:
        mid = (lo + hi) >> 1
        
        cnt = sum([mid // time for time in times])
        
        if cnt >= n:
            hi = mid
            continue
        lo = mid
    return hi