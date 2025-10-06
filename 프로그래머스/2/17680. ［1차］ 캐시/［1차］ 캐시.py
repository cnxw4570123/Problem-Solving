from heapq import heappop, heapify

def solution(cacheSize, cities):
    if cacheSize == 0:
        return 5 * len(cities)
    
    cache = []
    answer = 0
    for city in cities:
        cache_hit = False
        # hit -> 갱신
        for i in range(len(cache)):
            if cache[i][1] != city.casefold():
                continue
            cache[i][0] = answer
            answer += 1
            cache_hit = True
            heapify(cache)
            break

        if cache_hit:
            continue
        # miss -> cache에 추가            
        if len(cache) >= cacheSize:
            heappop(cache)
        cache.append([answer, city.casefold()])
        answer += 5
    
    return answer