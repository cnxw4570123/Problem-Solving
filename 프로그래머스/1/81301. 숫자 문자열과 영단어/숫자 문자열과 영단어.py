def solution(s):
    alpha_number = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
    answer = 0
    for digit, alphabet in enumerate(alpha_number):
        if alphabet in s:
            s = s.replace(alphabet, str(digit))
    answer = int(s)
    return answer