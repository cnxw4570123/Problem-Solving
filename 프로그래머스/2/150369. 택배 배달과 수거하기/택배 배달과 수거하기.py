def solution(cap, n, deliveries, pickups):
    # 둘 중 하나만 살아도 계속 이동
    dist = 0
    while deliveries or pickups:
        remove_empty_route(deliveries)
        remove_empty_route(pickups)
        dist += max(len(deliveries), len(pickups)) * 2
        
        calc_boxes(deliveries, cap)
        calc_boxes(pickups, cap)
        
    return dist

def remove_empty_route(l: list):
    while l and not l[-1]:
        l.pop()
        
def calc_boxes(l: list, cap: int):
    temp_box = 0
    for house in range(len(l) -1, -1, -1):
        if cap < temp_box + l[house]:
            l[house] -= cap - temp_box
            return
        temp_box += l[house]
        l[house] = 0