import java.util.*;

class Solution {
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static boolean[] v;
    public int solution(int n, int[][] computers) {
        v = new boolean[computers.length];
        int answer = 0;		
        for(int i = 0; i < computers.length; i++){
            if(v[i]){
                continue;
            }
            v[i] = true;
            DFS(i, computers);
            answer++;
        }

        return answer;
    }
    
    public void DFS(int x, int[][] computers){
		for(int i = 0; i < computers[x].length; i++){
            if(computers[x][i] == 0 || v[i]){
                continue;
            }
            v[i] = true;
            DFS(i, computers);
        }
    }
    
    
}