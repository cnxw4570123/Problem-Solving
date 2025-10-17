from collections import defaultdict

def solution(want, number, discount):
    jeonghyun = defaultdict(int)
    window, ans = 10, 0
    
    for i in range(10):
        jeonghyun[discount[i]] += 1
    #첫째날 바로 구매 가능.
    if all(jeonghyun[want[i]] == number[i] for i in range(len(number))):
        ans += 1

    #둘째날부터 가능.
    for i in range(len(discount) - window):
        jeonghyun[discount[i]] -= 1
        jeonghyun[discount[i + window]] += 1
        if all(jeonghyun[want[j]] == number[j] for j in range(len(number))):
        	ans += 1
        
    return ans