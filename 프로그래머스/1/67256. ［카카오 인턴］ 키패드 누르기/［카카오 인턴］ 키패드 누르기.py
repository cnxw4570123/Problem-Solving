HANDS = {"left" : (0, "L"), "right" : (1, "R")}

def solution(numbers, hand):
    thumbs = ["*", "#"]

    positions = {
        1:(0, 0), 2:(0, 1), 3:(0, 2),
        4:(1, 0), 5:(1, 1), 6:(1, 2),
        7:(2, 0), 8:(2, 1), 9:(2, 2),
        "*":(3, 0), 0:(3, 1), "#":(3,2)
    }
    answer = []
    for number in numbers:
        if number in (1, 4, 7):
            thumbs[0] = number
            answer.append("L")
            continue
        
        if number in (3, 6, 9):
            thumbs[1] = number
            answer.append("R")
            continue
            
        idx, direction = process(positions[thumbs[0]], positions[thumbs[1]], positions[number], hand)
        thumbs[idx] = number
        answer.append(direction)
            
        
    return "".join(answer)

def get_dist(pos1, pos2):
    return abs(pos1[0] - pos2[0]) + abs(pos1[1] - pos2[1])

def process(pos1, pos2, number_pos, hand):
    left, right = get_dist(pos1, number_pos), get_dist(number_pos, pos2)
    if left == right:
        return HANDS[hand]
    
    if left > right:
        return HANDS["right"]
    
    return HANDS["left"]
    