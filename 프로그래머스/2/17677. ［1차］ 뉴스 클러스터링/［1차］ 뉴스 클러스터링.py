import re

def solution(str1, str2):
    a_dict, b_dict = process(str1), process(str2)
    
    intersection, union = 0, 0
    # intersection
    if len(a_dict) >= len(b_dict):
        intersection, union = calculate(a_dict, b_dict)
    else:
        intersection, union = calculate(b_dict, a_dict)
    
    if not union:
        return 65536
    answer = int(intersection * 65536 / union)
    return answer

def process(string):
    res = {}
    string = string.lower()
    for i in range(len(string) - 1):
        candidate = string[i:i+2]
        if not re.match(r'[a-z]{2,}', candidate):
            continue
        res[candidate] = res.get(candidate, 0) + 1
    return res

def calculate(a_dict, b_dict):
    intersection, union = 0, 0
    for key, value in a_dict.items():
        intersection += min(a_dict[key], b_dict.get(key, 0))
        union += max(a_dict[key], b_dict.get(key, 0))

    for key, value in b_dict.items():
        if a_dict.get(key, 0) != 0:
            continue
        union += b_dict[key]
    return intersection, union