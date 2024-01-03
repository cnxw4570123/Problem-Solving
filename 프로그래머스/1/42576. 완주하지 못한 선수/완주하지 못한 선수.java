import java.util.*;
import java.util.Map.Entry;

class Solution {
    static Map<String, Integer> complete = new HashMap<>();
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        for(String name : participant){
            complete.put(name, complete.getOrDefault(name, 0) + 1);
        }
        
        for(String name : completion){
            complete.put(name, complete.get(name) - 1);
            
        }
        
        for(Entry<String, Integer> entry : complete.entrySet()){
            if(entry.getValue() > 0){
                return entry.getKey();
            }
        }
        return answer;
    }
}