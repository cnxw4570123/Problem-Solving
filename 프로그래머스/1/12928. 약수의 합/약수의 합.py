import math

def solution(n):
    answer = 0

    for i in range(1, int(math.sqrt(n)) + 1):
        if n % i != 0:
            continue
        
        answer += i
        if i ** 2 == n:         
            continue
        answer += n // i
        
    return answer