import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = lowerBound(times, n);
        return answer;
    }
    
    static long lowerBound(int[] times, int n){
        long start = 1, end = (long) times[times.length - 1] * n;
        while(start < end){
            long mid = (start + end) / 2;
			long count = 0;
            for(int time : times){
                count += mid / time;
            }
			
            if(count < n){
				start = mid + 1;
            } else{
                end = mid;
            }
        }
        return end;
    }
}