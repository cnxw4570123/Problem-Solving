import java.util.*;

class Solution {
    Set<Integer> s = new HashSet<>();
    public int solution(int[] nums) {
        for(int num : nums){
            s.add(num);
        }
        int answer = Math.min(s.size(), nums.length/ 2);
        return answer;
    }
    

}