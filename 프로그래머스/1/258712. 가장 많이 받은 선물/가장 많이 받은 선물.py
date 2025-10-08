from collections import defaultdict
from itertools import combinations 

gift_index = {}
gift_history = defaultdict(lambda : [0, 0])

def solution(friends, gifts):
    global gift_index, gift_history
    gift_index = {friend : 0 for friend in friends}
    predict = {friend : 0 for friend in friends}
    
    for gift in gifts:
        _from, to = gift.split()
        gift_index[_from] += 1
        gift_index[to] -= 1
        
        key = tuple(sorted([_from, to]))
        gift_history[key][key.index(_from)] += 1

    print(gift_history)
    for a, b in combinations(friends, 2):
        res = process(sorted([a, b]))
        if res == None:
            continue
        
        predict[res] += 1
    print(predict)
    answer = max(predict.values())
    return answer


def process(key):
    # 이미 정렬된 상태
    a, b = key
    res = gift_history[tuple(key)]
    
    if res[0] == res[1]:
        if gift_index[a] == gift_index[b]:
            return None
        if gift_index[a] > gift_index[b]:
            return a
        return b
    
    if res[0] > res[1]:
        return a
    return b
