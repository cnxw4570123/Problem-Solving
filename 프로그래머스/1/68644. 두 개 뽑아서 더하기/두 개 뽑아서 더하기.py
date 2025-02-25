from itertools import combinations

def solution(numbers):
    tmp = set()
    
    for a, b in combinations(numbers, 2):
        tmp.add(a + b)
    answer = sorted(list(tmp))
    return answer