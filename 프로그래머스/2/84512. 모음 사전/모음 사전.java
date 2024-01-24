class Solution {
	static char[] elem = {'A', 'E', 'I', 'O', 'U'};
    static boolean[] v = new boolean[5];
    static StringBuffer sb = new StringBuffer();
    static int ans = 0, res;
    public int solution(String word) {
        DFS(0, word);
        return ans;
    }
    
    static void DFS(int count, String word) {
        if (word.contentEquals(sb)) {
            ans = res;
            return;
        }
        if (count == 5) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (v[i]) {
                continue;
            }
            res++;
            sb.append(elem[i]);
            DFS(count + 1, word);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
}