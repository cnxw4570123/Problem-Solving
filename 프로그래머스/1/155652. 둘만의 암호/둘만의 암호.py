ALPHABET = "abcdefghijklmnopqrstuvwxyz"
def solution(s, skip, index):
    ans = []
    
    for c in s:
        temp_idx, mv = ALPHABET.find(c), index
        while mv > 0:
            temp_idx = (temp_idx + 1) % len(ALPHABET)
            if(ALPHABET[temp_idx] in skip):
                continue
            mv -= 1
        
        ans.append(chr(temp_idx + 97))            
    
    return ''.join(map(str, ans))