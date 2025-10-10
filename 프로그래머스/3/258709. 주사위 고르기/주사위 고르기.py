from itertools import combinations
from collections import Counter, defaultdict

dp = defaultdict(Counter)

def solution(dice):
    global dp
	
    res = defaultdict(lambda : [0] * 3)
    answer, max_res = [], [0, 0, 0]
    n = len(dice)
    for i in range(1, n + 1):
        dp[i] = Counter(dice[i - 1])
    
    for comb in combinations(range(1, n + 1), n // 2):
        if dp[comb]:
            continue

        me = make_comb(comb)
        opposite = tuple([i for i in range(1, n + 1) if not i in comb])
        other = make_comb(opposite)
        res[comb], res[opposite] = calculate(me, other)
        
        for i in range(3):
            if max_res[i] > res[comb][i]:
                break
            max_res[i:] = res[comb][i:]
            answer = comb
            break
        
        for i in range(3):
            if max_res[i] > res[opposite][i]:
                break
            max_res[i:] = res[opposite][i:]
            answer = opposite
            break
		        
    return answer

def make_comb(combination):
    global dp
    
    if dp[combination]:
        return dp[combination]
    
    if len(combination) == 1:
        return dp[combination[0]]
    
    min = len
    res1 = make_comb(combination[:-1])
    res2 = make_comb(combination[-1])
    
    for k1, v1 in res1.items():
        for k2, v2 in res2.items():
            dp[combination][k1 + k2] += v1 * v2
    
    return dp[combination]


def calculate(a, b):
    a_res = [0, 0, 0]
    for k1, v1 in a.items():
        for k2, v2 in b.items():
            value = v1 * v2
            if k1 > k2:
                a_res[0] += value
            elif k1 == k2:
                a_res[1] += value
            else:
                a_res[2] += value
    b_res = list(reversed(a_res))
    return a_res, b_res
                