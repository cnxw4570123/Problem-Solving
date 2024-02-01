import java.util.*;

class Solution {
    static ArrayDeque<int[]> s = new ArrayDeque<>();

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i = 0; i < prices.length; i++){
            // 큐에 최소 하나 이상의 원소가 있고 큐에 있는 원소가 가격이 더 크다면
            while(!s.isEmpty() && s.peekLast()[0] > prices[i]){
                // 큐에서 원소를 빼서 인덱스를 계산함.
                int[] pop = s.pollLast();
                //System.out.println(Arrays.toString(pop) + " " + i);
                answer[pop[1]] = i - pop[1];
            }
            s.add(new int[]{prices[i], i});
        }
        while(!s.isEmpty()){
            int[] pop = s.pollLast();
            answer[pop[1]] = prices.length - 1 - pop[1];
        }
        return answer;
    }
}