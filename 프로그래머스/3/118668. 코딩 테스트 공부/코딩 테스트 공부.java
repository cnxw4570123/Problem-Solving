import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static int[] maxP;
    static int[][] dp;
    static List<int[]> newProblems = new ArrayList<>();
    
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
            
        maxP = findMax(problems);
        alp = Math.min(alp, maxP[0]);
        cop = Math.min(cop, maxP[1]);
        dp = new int[maxP[0] + 1][maxP[1] + 1];
        List<int[]> org = Arrays.stream(problems).collect(Collectors.toList());
        newProblems.add(new int[]{0, 0, 1, 0, 1});
        newProblems.add(new int[]{0, 0, 0, 1, 1});
        newProblems.addAll(org);
        
        for(int[] temp : dp){
            Arrays.fill(temp, Integer.MAX_VALUE / 2);
        }
        
        dp[alp][cop] = 0;
        for (int i = alp; i < maxP[0] + 1; i++) {
            for (int j = cop; j < maxP[1] + 1; j++) {
                for (int[] prob : newProblems) {
                    int reqAlp = prob[0], reqCop = prob[1], rwdAlp = prob[2], rwdCop = prob[3], cost = prob[4];
                    if (i < reqAlp || j < reqCop) {
                        continue;
                    }
                    int ny = Math.min(maxP[0], i + rwdAlp), nx = Math.min(maxP[1], j + rwdCop);
                    dp[ny][nx] = Math.min(dp[i][j] + cost, dp[ny][nx]);
                }
            }
        }
        return dp[maxP[0]][maxP[1]];
    }
    
    static int[] findMax(int[][] probs){
        int[] max = {0,0};
        for(int[] temp : probs){
            if(max[0] < temp[0]){
                max[0] = temp[0];
            }
            if(max[1] < temp[1]){
                max[1] = temp[1];
            }
        }
        return max;
    }
    
}

/*
dp[a][b] = c -> 알고력 a와 코딩력 b가 되기위한 최소 시간은 c이다

if(a > reqAlp && b > reqCop){
    dp[a + rwdAlp][b + rwdCop] = Math.min(rwdAlp + rwdCop, cost);
}

*/