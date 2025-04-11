import java.util.*;

class Solution {
	static int[] v;
    static int UNVISITED = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        v = new int[words.length];
        Arrays.fill(v, UNVISITED);

        
        return BFS(begin, target, words);
    }
    
    public int BFS(String begin, String target, String[] words){
        Queue<String> q = new ArrayDeque<>();
       	q.add(begin);
        
        int t = 0;
        while(!q.isEmpty()){
			int qSize = q.size();
//      		System.out.println(q);
            
            for(int i = 0; i < qSize; i++){
                String current = q.poll();
//                System.out.println("current : " + current + ", time : " + t);
                if(current.equals(target)){
                    return t;
                }

                for(int j = 0; j < words.length; j++){
                    if(v[j] != UNVISITED || !canTransform(current, words[j])){
                        continue;
                    }
                    
                    v[j] = t;
                    q.add(words[j]);
                }
            }
            t++;
        }
		return 0;
    }
    
    
    public boolean canTransform(String from, String to){
        int cnt = 0;
        for(int idx = 0; idx < from.length(); idx++){
            if(from.charAt(idx) != to.charAt(idx)){
                cnt++;
            }
        }
        return cnt == 1;
    }
}