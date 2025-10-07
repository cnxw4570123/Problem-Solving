
def solution(numbers, hand):
    thumbs = ["*", "#"]
    hands = {"left" : (0, "L"), "right" : (1, "R")}

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
            
        left_dist = get_dist(positions[number], positions[thumbs[0]])
        right_dist = get_dist(positions[number], positions[thumbs[1]])

        if left_dist == right_dist:
            idx, direction = hands[hand]
            thumbs[idx] = number
            answer.append(direction)
            continue
        
        if left_dist > right_dist:
            thumbs[1] = number
            answer.append("R")
            continue

        thumbs[0] = number
        answer.append("L")
            
        
    return "".join(answer)

def get_dist(pos1, pos2):
    return abs(pos1[0] - pos2[0]) + abs(pos1[1] - pos2[1])