from collections import defaultdict
max_diff, answer = 0, [0] * 11
def solution(n, info):
    apeach = sum([10 - i for i in range(11) if info[i]])
    DFS(info, n, 0, apeach, 0)
    if not max_diff:
        return [-1]
    return answer

def DFS(info, arrow_left, idx, other, points, history = [0] * 11):
    global answer, max_diff
    # 점수 계산
    if not arrow_left:
        if max_diff < (points - other):
            max_diff = points - other
            answer = [history[i] for i in range(11)]
            return
        
        if max_diff == (points - other):
            print(
            f"""기록 : {answer}\n대체재 : {history}""")
            for i in range(10, -1, -1):
                if answer[i] == history[i]:
                    continue
                if answer[i] > history[i]:
                    return
                answer = [history[i] for i in range(11)]
        return
    
    for i in range(idx, 11):
        # 남은 화살 + 1보다 어피치가 맞춘 화살 개수가 더 크면 점수 포기
        if info[i] + 1 > arrow_left:
            continue
        history[i] = info[i] + 1
        new_history = [history[i] for i in range(11)]
        if info[i]:
            DFS(info, arrow_left - history[i], i + 1, other - 10 + i, points + 10 - i, new_history)
        else:
            DFS(info, arrow_left - history[i], i + 1, other, points + 10 - i, new_history)
        history[i] = 0
    else:
        history[10] += arrow_left
        new_history = [history[i] for i in range(11)]
        DFS(info, 0, 11, other, points, new_history)
        history[10] = 0
        return
        