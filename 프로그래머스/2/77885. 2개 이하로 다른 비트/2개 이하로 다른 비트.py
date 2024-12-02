def solution(numbers):
    ans = []
    for number in numbers:
        binary = bin(number)[2:]
        if binary.count("1") == len(binary):
            temp = number | (1 << len(binary))
            ans.append(temp & ~(1 << len(binary) - 1))
            continue
        
        for idx in range(len(binary) + 1):
            if 1 << idx & number == 0:
                ans.append(number | (1 << idx))
                break
    return ans