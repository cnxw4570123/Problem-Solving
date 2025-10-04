import re
def solution(new_id):
    ans = new_id.lower()
    ans = re.sub(r"[^a-z0-9-_.]", "", ans)
    ans = re.sub(r"\.{2,}", ".", ans)
	
    ans = ans.strip(".")
    
    
    if not ans:
        ans = "a"
        
    if len(ans) >= 16:
        ans = ans[:15].rstrip(".")
        
    if len(ans) <= 2:
        ans += ans[-1] * (3 - len(ans))
    
    return ans