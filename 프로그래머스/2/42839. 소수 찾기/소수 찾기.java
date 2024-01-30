import java.util.*;

class Solution {
    static int size, MAX = 10_000_000;
    static boolean[] v = new boolean[8], isNotPrime = new boolean[MAX];
    static Set<Integer> ans = new HashSet<>();
    public int solution(String numbers) {
        era();
        size = numbers.length();
        for(int i = 1; i <= size; i++){
            DFS(0, i, numbers, "");
        }
        return ans.size();
    }
    
    static void DFS(int count, int n, String numbers, String res){
        if(count == n){
            int num = Integer.parseInt(res);
            if(isNotPrime[num]) return;
            ans.add(num);
            return;
        }
        
        for(int i = 0; i < size; i++){
            if(v[i]) continue;
            v[i] = true;
            DFS(count + 1, n, numbers, res + numbers.charAt(i));
            v[i] = false;
        }
    }
    
    static void era(){
        isNotPrime[0] = isNotPrime[1] = true;
        for(int i = 2; i < Math.sqrt(MAX) + 1; i++){
            for(int j = i * 2; j < MAX; j += i){
                if(isNotPrime[j]) continue;
                isNotPrime[j] = true;
            }
        }
    }
}