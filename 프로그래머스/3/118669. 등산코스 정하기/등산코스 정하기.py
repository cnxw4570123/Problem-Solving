from heapq import heappush, heappop

INF = float('inf')

def solution(n, paths, gates, summits):
    global graph
    answer = [0, INF]
    graph = [[] for _ in range(n + 1)]
    for s, e, c in paths:
        graph[s].append((e, c))
        graph[e].append((s, c))
    
    summits.sort()
    intensities = dijkstra(gates, summits, n)
    
    for i in range(len(summits)):
        if answer[1] <= intensities[summits[i]]:
            continue
        answer[0], answer[1] = summits[i], intensities[summits[i]] 
        
    return answer

def dijkstra(gates, summits, n):
    max_intensity = [INF] * (n + 1)
    hq = []
    for gate in gates:
        max_intensity[gate] = 0
        heappush(hq, (0, gate))

    while hq:
        intensity, node = heappop(hq)
        
        if max_intensity[node] < intensity or node in summits:
            continue
        
        for next_node, next_intensity in graph[node]:
            new_intensity = max(next_intensity, intensity)
            
            if max_intensity[next_node] <= new_intensity:
                continue
            
            max_intensity[next_node] = new_intensity
            heappush(hq, (new_intensity, next_node))
    
    return max_intensity