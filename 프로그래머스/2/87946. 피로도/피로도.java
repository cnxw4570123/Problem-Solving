import java.util.*;

class Solution {
    static boolean[] v = new boolean[8];
    static int ans = 0;
    public int solution(int k, int[][] dungeons) {
        DFS(0, k, dungeons);
        
        return ans;
    }
    
    static void DFS(int count, int energy, int[][] dungeons){
        ans = Math.max(count , ans);
        if(energy == 0 || count == dungeons.length){
            return;
        }
        
        for(int i = 0; i < dungeons.length; i++){
            if(v[i] || energy < dungeons[i][0]) continue;
            v[i] = true;
            DFS(count + 1, energy - dungeons[i][1], dungeons);
            v[i] = false;
        }
    }
}