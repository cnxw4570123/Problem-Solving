import java.util.*;

class Solution {
    static Map<Integer, Integer> count = new HashMap<>();
    public int solution(int[] citations) {
        int answer = 0;
        Integer[] array = Arrays.stream(citations)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);

        for(int i = 0; i < array.length; i++){
            if(array[i] >= i + 1){
                answer = Math.max(answer, i + 1);
            }
        }
        
        return answer;
    }
}