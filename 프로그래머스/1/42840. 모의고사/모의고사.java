import java.util.*;
import java.util.Map.Entry;

class Solution {
    static int[] A = {1, 2, 3, 4, 5}, B = {2, 1, 2, 3, 2, 4, 2, 5}, 
    C = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    static Map<Integer, Integer> scores = new HashMap<>();
    public int[] solution(int[] answers) {
        for(int i = 0; i < answers.length; i++){
            int answer = answers[i];
            if(A[(i + A.length) % A.length] == answer){
                scores.put(1, scores.getOrDefault(1, 0) + 1);
            }
            if(B[(i + B.length) % B.length] == answer){
                scores.put(2, scores.getOrDefault(2, 0) + 1);
            }
            if(C[(i + C.length) % C.length] == answer){
                scores.put(3, scores.getOrDefault(3, 0) + 1);
            }
        }
        int max = scores.values().stream()
            .max(Integer::compareTo).orElseGet(() -> 0);
		return scores.entrySet().stream()
            .filter(e -> e.getValue() == max)
            .sorted((e1, e2) -> e1.getKey() - e2.getKey())
            .mapToInt(Entry::getKey)
            .toArray();
    }
}