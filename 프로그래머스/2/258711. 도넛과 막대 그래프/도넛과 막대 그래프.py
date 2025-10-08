from collections import defaultdict, deque

graph = defaultdict(list)

def solution(edges):
    global graph, e
    in_degrees = defaultdict(int)
    
    answer = [0, 0, 0, 0]
    for _from, to in edges:
        in_degrees[_from] += 0
        in_degrees[to] += 1
        graph[_from].append(to)
    
    for key, value in in_degrees.items():
        if not value and len(graph[key]) >= 2:
            answer[0] = key
            break
	
    for start in graph[answer[0]]:
        e, v = BFS(start)
        
        # 도넛 그래프 n, n
        if len(v) == len(e):
            answer[1] += 1
            continue
        # 막대 그래프 n, n - 1
        if len(v) - 1 == len(e):
            answer[2] += 1
            continue
        # 8자 그래프 2* n + 1, 2 * n + 2
        answer[3] += 1

    
    return answer

def BFS(current):
    q = deque([current])
    v, e = set([current]), set()
    
    while q:
        current = q.popleft()
        
        for next in graph[current]:
            if (current, next) in e:
                continue
            q.append(next)
            v.add(next)
            e.add((current, next))

    return e, v
