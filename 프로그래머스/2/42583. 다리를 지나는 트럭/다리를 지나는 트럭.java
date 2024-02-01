import java.util.*;

class Solution {
    // 트럭 무게, 들어온 시간
    static Queue<int[]> q = new ArrayDeque<>();
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int current_weight = 0, current_time = 0;
        for(int i = 0; i < truck_weights.length; i++){
            if(!q.isEmpty() && current_time - q.peek()[1] >= bridge_length){
                current_weight -= q.peek()[0];
                q.poll();
            }
            boolean removed = false;
			while(current_weight + truck_weights[i] > weight){
                removed = true;
                int[] truck = q.poll();
                current_weight -= truck[0];
                if(current_time - truck[1] < bridge_length){
                    current_time += bridge_length - (current_time - truck[1]);
                }
            }
            if(!removed){
                current_time++;
            }
            current_weight += truck_weights[i];
            q.add(new int[]{truck_weights[i], current_time});
        }
        
        while(!q.isEmpty()){
            int[] truck = q.poll();
            current_time += bridge_length - (current_time - truck[1]);
        }
        return current_time;
    }
}