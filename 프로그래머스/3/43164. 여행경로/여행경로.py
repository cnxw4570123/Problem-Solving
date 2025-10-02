from collections import defaultdict


flag = False
ans = []
def solution(tickets):
    global ans
    graph = defaultdict(list)

    id = 0
    for _from, to in tickets:
        graph[_from].append((to, id))
        id += 1
    
    for key, value in graph.items():
        graph[key] = sorted(value)
        
    find(graph, tickets, "ICN", [])
    res = ["ICN"] + [ans[i][1] for i in range(len(ans))]
    return res

def find(graph, tickets, current, v):
    global flag, ans
    if flag:
        return
    
    if not flag and len(v) == len(tickets):
        ans = v
        flag = True
        return
    
    for next, id in graph[current]:
        if (current, next, id) in v:
            continue

        nv = list(v)
        nv.append((current, next, id))
        find(graph, tickets, next, nv)
            
        
        