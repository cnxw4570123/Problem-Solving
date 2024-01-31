import java.util.*;

class Solution {
    static long[] fact;
    static Map<String, Integer> counts = new HashMap<>();
    static String[] category;
    public int solution(String[][] clothes) {
        for(String[] cloth : clothes){
            counts.put(cloth[1], counts.getOrDefault(cloth[1], 0) + 1);
        }
        
        System.out.println(counts);
		int ans = counts.values().stream().
            reduce(1, (a, b) -> a * (b + 1));
        
        return ans - 1;
    }
}