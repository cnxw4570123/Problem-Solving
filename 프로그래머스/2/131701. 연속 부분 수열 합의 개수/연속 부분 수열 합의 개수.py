def solution(elements):
    MAX = len(elements)
    answer = set()
    dp = [[0] * (MAX + 1) for _ in range(MAX + 1)]

    for i in range(1, MAX + 1):
        for w in range(1, MAX + 1):
            if i + w - 1 > MAX:
                dp[i][w] = dp[i][MAX - i + 1] + dp[1][w + i - MAX - 1]
            else:
                dp[i][w] = dp[i][w - 1] + elements[i + w - 2]
            answer.add(dp[i][w])
            
    return len(answer)