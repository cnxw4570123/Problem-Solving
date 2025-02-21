def solution(x):
    sum, temp = 0, x
    while temp != 0:
        sum += temp % 10
        temp //= 10
    
    return x % sum == 0