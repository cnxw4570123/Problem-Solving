import java.util.*;

class Solution {
    static List<Integer> numbers = new ArrayList<>();
    static Queue<int[]> q = new ArrayDeque<>();
    public List<Integer> solution(int[] progresses, int[] speeds) {
        int day = 0;
        for(int i = 0; i < progresses.length; i++){
            q.add(new int[]{progresses[i], speeds[i]});
        }
        while(!q.isEmpty()){
            int count = 0;
            while(!q.isEmpty() && q.peek()[0] + q.peek()[1] * day >= 100){
                q.poll();
                count++;
            }
            if(count != 0){
                numbers.add(count);                    
            }

            if(q.isEmpty()){
                break;
            }
            int[] cur = q.peek();
            int plus = (100 - cur[0] - (cur[1] * day)) / cur[1];
            day += (plus == 0 ? 1 : plus);
        }
        return numbers;
    }
}