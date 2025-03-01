def solution(cap, n, deliveries, pickups):
    # 둘 중 하나만 살아도 계속 이동
    dist = 0
    while deliveries or pickups:
        remove_empty_route(deliveries)
        remove_empty_route(pickups)
        delivery_box = 0
        dist += max(len(deliveries), len(pickups)) * 2
        
        for house in range(len(deliveries) - 1, -1, -1):
            if cap < delivery_box + deliveries[house]:
                deliveries[house] -= cap - delivery_box
                break
            delivery_box += deliveries[house]
            deliveries[house] = 0
        
        pickup_box = 0
        for house in range(len(pickups) -1, -1, -1):
            if cap < pickup_box + pickups[house]:
                pickups[house] -= cap - pickup_box
                break
            pickup_box += pickups[house]
            pickups[house] = 0
        
    return dist

def remove_empty_route(l: list):
    while l and not l[-1]:
        l.pop()