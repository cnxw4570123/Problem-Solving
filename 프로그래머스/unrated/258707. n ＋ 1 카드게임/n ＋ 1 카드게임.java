import java.util.*;


class Solution {
    static int N, coinLeft, answer = 1, idx, ans = 0;
    static Set<Integer> hand = new HashSet<>(), draw = new HashSet<>();
    public int solution(int coin, int[] cards) {
        init(cards, coin);
        return proceedGame(cards);
    }
    
   static void init(int[] cards, int coin) {
        N = cards.length + 1;
        idx = cards.length / 3;
        coinLeft = coin;
        for (int i = 0; i < idx; i++) {
            hand.add(cards[i]);
        }
    }
    static int proceedGame(int[] cards){
        int round = 1;
        while(idx + 2 <= cards.length){
            draw.add(cards[idx++]);
            draw.add(cards[idx++]);
            if(search(hand, hand)){
                round++;
                continue;
            }
            if(coinLeft > 0 && search(hand, draw)){
                coinLeft--;
                round++;
                continue;
            }
            if(coinLeft > 1 && search(draw, draw)){
                coinLeft-=2;
                round++;
                continue;
            }
            break;
        }
        return round;
    }
    
    static boolean search(Set<Integer> a, Set<Integer> b){
        for(int num : a){
            if(b.contains(N - num)){
                b.remove(N - num);
                a.remove(num);
                return true;
            }
        }
        return false;
    }
}