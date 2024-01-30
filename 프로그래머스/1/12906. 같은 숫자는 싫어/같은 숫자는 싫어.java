import java.util.*;

public class Solution {
    static ArrayDeque<Integer> s = new ArrayDeque<>();
    public int[] solution(int []arr) {
        s.add(arr[0]);
        for(int i = 1; i < arr.length; i++){
            if(s.peekLast() == arr[i]) continue;
            s.add(arr[i]);
        }
        int[] answer = new int[s.size()];
        for(int i = 0; !s.isEmpty(); i++){
            answer[i] = s.poll();
        }
        return answer;
    }
}