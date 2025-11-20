INF = float("inf")


def solution(n, s, a, b, fares):
    global costs
    costs = [[INF if j != i else 0 for j in range(n + 1)] for i in range(n + 1)]
	
    for start, end, cost in fares:
        costs[start][end] = cost
        costs[end][start] = cost
    
    
    for j in range(1, n + 1):
        for i in range(1, n + 1):
            for k in range(1, n + 1):
                if costs[i][j] + costs[j][k] >= costs[i][k]:
                    continue
                costs[i][k] = costs[i][j] + costs[j][k]
	
    
    answer = costs[s][a] + costs[s][b]
   
        
    for i in range(1, n + 1):
        if answer <= costs[a][i] + costs[b][i] + costs[s][i]:
            continue
        answer = costs[a][i] + costs[b][i] + costs[s][i]
        
    return answer
