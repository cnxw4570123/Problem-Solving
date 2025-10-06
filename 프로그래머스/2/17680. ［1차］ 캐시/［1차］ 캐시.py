from heapq import heappop, heappush, heapify, heappushpop, heapify

def solution(cacheSize, cities):
    if not cacheSize:
        return 5 * len(cities)
    
    cache = []
    answer = 0
    for city in cities:
        cache_hit = False
        # hit -> 갱신
        for time, target_city in iter(cache):
            if target_city != city.casefold():
                continue
            #print(f"{answer}초 cache hit = ({time}, {target_city})")
            cache_hit = True
            cache.remove((time, target_city))
            heapify(cache)
            break
		# 힙 사이즈가 꽉 찼을 때 캐시 힛 => 캐시에서 삭제
        answer += 1 if cache_hit else 5
            
        if len(cache) == cacheSize:
            heappushpop(cache, (answer, city.casefold()))
        else:	
            heappush(cache, (answer, city.casefold()))

    
    return answer