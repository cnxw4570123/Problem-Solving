import re
def solution(new_id):
    ans = new_id.lower()
    ans = re.sub(r"[^a-z0-9-_.]", "", ans)
    ans = re.sub(r"\.{2,}", ".", ans)

    if ans.startswith("."):
        ans = ans[1:]
    if ans.endswith("."):
        ans = ans[:-1]
    
    if not ans:
        ans = "a"
    if len(ans) >= 16:
        ans = ans[:15]
        if ans[-1] == ".":
            ans = ans[:-1]
	
    if len(ans) <= 2:
        ans += ans[-1] * (3 - len(ans))
    
    return ans