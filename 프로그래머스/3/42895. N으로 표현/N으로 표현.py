operations = [lambda x, y: x + y, lambda x, y : x * y, lambda x, y: x - y, lambda x, y: x // y]

res_by_cnt = []
def solution(N, number):
    num = N
    for _ in range(1, 9):
        res_by_cnt.append(set([num]))
        num = num * 10 + N
	
    if N == number:
        return 1
    
    for i in range(1, 8):
        for j in range(i):
            for current in res_by_cnt[j]:
                for prev in res_by_cnt[i - j - 1]:		
                    calculate(prev, current, i)
                    
        if number in res_by_cnt[i]:
            return i + 1

    return -1
                    
def calculate(prev, current, index):
    for op in operations:
        if prev == 0:
            continue
        res_by_cnt[index].add(op(current, prev))