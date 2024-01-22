import java.util.*;

class Solution {
    static PriorityQueue<Integer> pqDesc = new PriorityQueue<>((i1, i2) -> i2 - i1);
    static PriorityQueue<Integer> pqAsc = new PriorityQueue<>();
    public int[] solution(String[] operations) {
        for(String oper : operations){
            String cmd = oper.split(" ")[0];
            int num = Integer.parseInt(oper.split(" ")[1]);
          
			if(cmd.equals("I")){
                pqDesc.add(num);
                pqAsc.add(num);
                continue;
            }
            if(num == 1){
				pqAsc.remove(pqDesc.poll());
                continue;
            }
            pqDesc.remove(pqAsc.poll());
            
        }
        Integer max = pqDesc.poll(), min = pqAsc.poll();
        if(max == null) max = 0;
        if(min == null) min = 0;
        return new int[]{max,  min};
    }
}