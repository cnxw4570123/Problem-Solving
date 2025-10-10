n = 0
def solution(coin, cards):
    global n
    possible = True
    n = len(cards)
    rounds = 1
    
    hand = cards[:n // 3]
    cards = cards[n//3:]
    draw = []
    
    while cards:
        draw += cards[:2]
        cards[:] = cards[2:]
        #print(f"draw = {draw}, cards = {cards}")
        if search(hand, hand):
            rounds += 1
            continue
        if coin > 0 and search(hand, draw):
            rounds += 1
            coin -= 1
            continue
        if coin > 1 and search(draw, draw):
            rounds += 1
            coin -= 2
            continue
        
        break
    return rounds


def search(a, b):
    for k in a:
        if n + 1 - k in b:
            b.remove(n + 1 - k)
            a.remove(k)
            return True
    return False
            