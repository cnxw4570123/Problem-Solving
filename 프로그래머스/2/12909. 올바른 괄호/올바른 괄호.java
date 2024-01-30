import java.util.*;

class Solution {
    static ArrayDeque<Character> stack = new ArrayDeque<>();

    boolean solution(String s) {
        boolean answer = true;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.add(c);
            } else{
                if(stack.isEmpty() || stack.peek() != '('){
                    return false;
                }
                stack.pollLast();
            }

        }
        if(!stack.isEmpty()){
            return false;
        }
        return answer;
    }
}