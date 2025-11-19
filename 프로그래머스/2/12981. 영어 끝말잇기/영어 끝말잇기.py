def solution(n, words):
    used_words = {words[0]}
	
    current = words[0]
    for i in range(1, len(words)):
        if current[-1] != words[i][0] or words[i] in used_words:
            return [(i % n) + 1, (i // n) + 1]
        current = words[i]
        used_words.add(words[i])
        
    return [0, 0]