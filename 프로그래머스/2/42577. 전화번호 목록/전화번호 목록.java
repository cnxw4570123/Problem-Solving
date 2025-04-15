import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Trie trie = new Trie();
        for(String phone : phone_book){
            if(trie.insert(phone)){
                return false;
            }
        }
       
        return true;
    }
	static class Trie{
        private Node root;
		
        public Trie(){
            this.root = new Node();
        }
        
        public boolean insert(String s){
            Node current = root;
            
            for(char ch : s.toCharArray()){
				current = current.child.computeIfAbsent(ch, c -> new Node());
                // 아직 넣을 글자 있는데 이미 단어 완성
				if(current.isEndOfWord){
                    return true;
                }
//                System.out.println("c = " + ch + ", 글자끝 ? " + current.isEndOfWord);
            }
            current.isEndOfWord = true;
            return !current.child.isEmpty();
        }
       
    }   
    
    static class Node{
        public Map<Character, Node> child;
        public boolean isEndOfWord;
        
        public Node(){
            this.child = new HashMap<>();
            this.isEndOfWord = false;
        }
    }
}