class Solution {
    static int innerW = Integer.MAX_VALUE, innerH = 1;
    public int[] solution(int brown, int yellow) {
        for(int i = 1; i < Math.sqrt(yellow) + 1; i++){
            if(yellow % i == 0){
                innerW = Math.min(innerW, yellow / i);
                innerH = Math.max(innerH, i);
                if(yellow + brown == (innerW + 2) * (innerH + 2)){
					return new int[]{innerW + 2, innerH + 2};
                }
            }
        }
        return new int[]{0, 0};
    }
}