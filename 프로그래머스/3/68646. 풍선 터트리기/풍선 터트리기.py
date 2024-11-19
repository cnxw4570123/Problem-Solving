import sys

MAX = sys.maxsize
NATURAL, REVERSE = 0, 1

def solution(a):
    answer = set()

    dp = [[MAX] * (len(a) + 1) for _ in range(2)]

    
    for i in range(1, len(a) + 1):
        dp[NATURAL][i] = min(dp[NATURAL][i - 1], a[i - 1])
        dp[REVERSE][i] = min(dp[REVERSE][i - 1], a[len(a) - i])
        
    for i in range(len(a)):
        left, mid, right = dp[NATURAL][i], a[i], dp[REVERSE][len(a) - (i + 1)]
        if left == MAX and right != MAX:
            answer.update([mid, right])
            continue
        if right == MAX and left != MAX:
            answer.update([left, mid])
            continue

        answer.update([min(left, mid), right])
        answer.update([left, min(mid, right)])
    return len(answer)
