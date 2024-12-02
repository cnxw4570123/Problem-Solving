def find(number):
    binary = bin(number)[2:]
    # 모든 하위 비트가 꽉 찼을 때 -> 최상위 비트 추가
    if binary.count("1") == len(binary):
        temp = number | (1 << len(binary))

        candidate = temp & ~(1 << len(binary) - 1)
        return candidate

    # 하위 비트에 꺼진 것이 있을 경우

    # 그 중 최소 비트가 꺼져있으면
    if number & (1 << 0) == 0:
        candidate = number | (1 << 0)
        return candidate

    for idx in range(len(binary) - 1, 1, -1):
        if binary[idx] != "1" or binary[idx - 1] != "0":
            continue
        # 켜진 하위 비트 끄기
        candidate = number & ~(1 << (len(binary) - idx - 1))
        # 꺼진 상위 비트 켜기
        candidate |= 1 << (len(binary) - idx)
        return candidate
    
def solution(numbers):
    ans = []
    for number in numbers:
        ans.append(find(number))
    return ans