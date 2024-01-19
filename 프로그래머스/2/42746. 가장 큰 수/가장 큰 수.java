import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(int[] numbers) {
        List<String> array = Arrays.stream(numbers)
			.mapToObj(Long::toString)
            .sorted((s1, s2) -> {
                if(s1.charAt(0) == s2.charAt(0)){
                    Long a1 = Long.parseLong(s1 + s2);
                    Long a2 = Long.parseLong(s2 + s1);
                    return Long.compare(a2, a1);
                }
                return s2.charAt(0) - s1.charAt(0);
            })
           	.collect(Collectors.toList());
        if(array.get(0).equals("0")){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for(String s : array){
            sb.append(s);
        }
        return sb.toString();
    }
}