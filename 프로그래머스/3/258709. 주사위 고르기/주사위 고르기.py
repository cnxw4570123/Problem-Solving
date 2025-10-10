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
        # (1, 2) (3, 4)와 (3, 4), (1, 2)는 같음.
        #if dp[comb]:
        #    continue

        me = make_comb(comb)
        opposite = [i for i in range(1, n + 1) if not i in comb]
        other = make_comb(tuple(opposite))
        res[comb] = calculate(me, other)
        
        for i in range(3):
            if max_res[i] > res[comb][i]:
                break
            max_res[i:] = res[comb][i:]
            answer = comb
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
    ans = [0, 0, 0] # 승 무 패
    for k1, v1 in a.items():
        for k2, v2 in b.items():
            if k1 > k2:
                ans[0] += v1 * v2
            elif k1 == k2:
                ans[1] += v1 * v2
            else:
                ans[2] += v1 * v2
    return ans
                