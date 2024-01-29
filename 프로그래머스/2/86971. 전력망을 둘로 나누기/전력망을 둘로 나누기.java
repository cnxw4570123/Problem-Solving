import java.util.*;

class Solution {
    static int N, answer = 101;
    static int[] parent;
    public int solution(int n, int[][] wires) {
        N = n;
        Arrays.sort(wires, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) {
                return arr1[1] - arr2[1];
            }
            return arr1[0] - arr2[0];
        });

        for (int i = 0; i < wires.length; i++) {
            init();
            for(int j = 0; j < wires.length; j++){
                if(i == j) continue;
                union(wires[j][0], wires[j][1]);
            }
            calculate();

        }	
        return answer;
    }

    static void init() {
        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        }
        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
    
    static void calculate(){
        int a = 0, b = 0;
        for(int i = 1; i < N + 1; i++){
            if(find(i) == 1){
                a++;
                continue;
            }
            b++;
        }
        answer = Math.min(answer, Math.abs(a - b));
    }
}