import java.util.*;

class Solution {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public int solution(int[] scoville, int K) {
        for(int num : scoville){
            pq.add(num);
        }
        int res = 0;
        int answer = 0;
        while(pq.size() >= 2){
            int min = pq.poll();
            if(min >= K){
                break;
            }
            int next = pq.poll();
            int newFood = min + next * 2;
            res = Math.max(res, newFood);
            answer++;
            pq.add(newFood);
        }
        System.out.println(res);
        if(answer != 0 && res < K){
            return -1;
        }
        return answer;
    }
}