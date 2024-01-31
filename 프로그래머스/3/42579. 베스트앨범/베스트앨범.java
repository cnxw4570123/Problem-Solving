import java.util.*;
import java.util.Map.Entry;
class Solution {
	static Map<String, Integer> sortByCount = new HashMap<>();
    static Map<String, PriorityQueue<Integer>> categories = new HashMap<>();
    static int[] play;
    static Comparator<Integer> comp = (i1, i2) -> {
    	// 재생 수가 같다면 번호 내림차순
    	if(play[i1] == play[i2]){
        	return i2 - i1;
        }
        // 재생 수 오름차순
        return play[i1] - play[i2];
    };
    public List<Integer> solution(String[] genres, int[] plays) {
        play = plays;
        for(int i = 0; i < genres.length; i++){
        	sortByCount.put(genres[i], sortByCount.getOrDefault(genres[i], 0) + plays[i]);
            PriorityQueue<Integer> pq = categories.getOrDefault(genres[i], new PriorityQueue<Integer>(comp));
            if(pq.size() < 2){
            	pq.add(i);
                categories.put(genres[i], pq);
                continue;
            }
            int cur = pq.peek();
            if(plays[cur] < plays[i]){
            	pq.poll();
                pq.add(i);
            }
            categories.put(genres[i], pq);
            
        }
        System.out.println(sortByCount);
		List<Integer> list = new ArrayList<>();
        sortByCount.entrySet().stream()
            .sorted((e1, e2) -> e1.getValue() - e2.getValue())
            .map(Entry::getKey)
            .forEach(key -> {
            	PriorityQueue<Integer> pq = categories.get(key);
	            System.out.println(pq);
    	        list.addAll(pq);    
            });
			
        Collections.reverse(list);
        return list;
    }
}