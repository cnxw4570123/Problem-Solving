import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        return find(n, times);
    }
    
    static long find(int people, int[] times){
        long start = 1, end = (long)people * times[times.length - 1] + 1;
        
        while(start < end){
			long mid = (start + end) >> 1;
            long possibleCnt = 0;
            for(int i = 0; i < times.length; i++){
                possibleCnt += mid / times[i];
            }
            
            if(possibleCnt >= people){
                end = mid;
                continue;
            }
            start = mid + 1;
        }
        
        return end;
    }
}