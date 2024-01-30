import java.util.*;

class Solution {
    static class Work{
        int start;
        int cost;

        Work(int start, int cost){
            this.start = start;
            this.cost = cost;
        }

        @Override
        public String toString(){
            return "Work = {" + start +", " + cost + "}";
        }
    }

    static PriorityQueue<Work> pqByStart = new PriorityQueue<>((w1, w2) -> {
        if(w1.start == w2.start){
            return w1.cost - w2.cost;
        }
        return w1.start - w2.start;
    });
    static PriorityQueue<Work> pqByCost = new PriorityQueue<>((w1,w2) -> {
        if(w1.cost == w2.cost){
            return w1.start - w2.start;
        }
        return w1.cost - w2.cost;
    });
	static int time, end;
    public int solution(int[][] jobs) {
        for(int[] job : jobs){
            pqByStart.add(new Work(job[0], job[1]));
        }
        int size = jobs.length;

        pqByCost.add(pqByStart.poll());
        while(!pqByCost.isEmpty()){
            Work current = pqByCost.poll();
            
            if(end < current.start){
                time += current.cost;
                end = current.start + current.cost;
            } else{
                // 요청시간보다 직전 작업의 끝난 시간이 더 오래걸린 경우 기다린 시간 + 작업 소요시간
                time += (end - current.start) + current.cost;
                end += current.cost;
            }
            
            // 이전 작업 끝난 시간까지 쌓인 일을 모두 저장
            while(!pqByStart.isEmpty() && pqByStart.peek().start <= end){
                pqByCost.add(pqByStart.poll());
            }
            // 직전 시간까지의 요청이 모두 처리된 경우 새로운 일을 저장
            if(!pqByStart.isEmpty() && pqByCost.isEmpty()){
                pqByCost.add(pqByStart.poll());
            }
        }
        return time / size;
    }
}