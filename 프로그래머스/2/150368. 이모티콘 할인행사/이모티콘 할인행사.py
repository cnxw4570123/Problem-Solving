from itertools import product


def solution(users, emoticons):
    answer = [0, 0]
    for row in product([10, 20, 30, 40], repeat = len(emoticons)):
        subscribers, margin = calculate(row, emoticons, users)
        if subscribers > answer[0]:
            answer = [subscribers, margin]
            continue
        if subscribers == answer[0]:
            answer[1] = max(answer[1], margin)


    return answer

def calculate(row, emoticons, users):
    subscribers, margin = 0, 0
    for min_sale, min_price in users:
        total_price = 0
        for i in range(len(emoticons)):
            if row[i] < min_sale:
                continue
            total_price += (100 - row[i]) * emoticons[i] // 100
            
        if total_price >= min_price:
            subscribers += 1
            continue
		
        margin += total_price

    return subscribers, margin

