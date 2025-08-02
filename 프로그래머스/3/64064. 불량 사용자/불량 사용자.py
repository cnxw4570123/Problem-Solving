from itertools import permutations
import re

def solution(user_id, banned_id):
    answer = set()
    
    for p in permutations(user_id, len(banned_id)):
        cnt = 0
        
        for i in range(len(p)):
            ban = banned_id[i].replace('*', '.')
            if re.match(ban, p[i]) and len(ban) == len(p[i]):
                cnt += 1
        if cnt == len(banned_id):
            p = tuple(sorted(p))
            answer.add(p)

    return len(answer)