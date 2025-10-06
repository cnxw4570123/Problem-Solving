from collections import defaultdict

result = defaultdict(int)

def solution(survey, choices):
    answer = ''
    for i in range(len(survey)):
        calculate(choices[i] - 1, survey[i])

    answer += "R" if result['R'] >= result['T'] else 'T'
    answer += 'C' if result['C'] >= result['F'] else 'F'
    answer += 'J' if result['J'] >= result['M'] else 'M'
    answer += 'A' if result['A'] >= result['N'] else 'N'
    return answer
	
def calculate(index, string):
    global result
    subjects = [(0, 3), (0, 2), (0, 1), (0, 0), (1, 1), (1, 2), (1, 3)]
    idx, points = subjects[index]
    result[string[idx]] += points
    