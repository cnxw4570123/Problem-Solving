import java.util.*;

class Solution {
    static boolean[][][] v;
    static List<Integer>[] graph;
    static int ans;

    public int solution(int[] info, int[][] edges) {
        init(info, edges);
        v[0][1][0] = true;
        info[0] = 2;
        DFS(info, 0, 1, 0);
        return ans;
    }

    static void init(int[] info, int[][] edges){
        int len = info.length + 1;
        graph = new List[len];
        v = new boolean[len][len][len];
        
        for(int i = 0; i < len; i++){
            graph[i] = new ArrayList<Integer>();
        }

        for (int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
    }

    static void DFS(int[] info, int node, int sheep, int wolf){
        ans = Math.max(ans, sheep);
        if(sheep + wolf == info.length){
            return;
        }
        //System.out.println("node = " + node +", sheep = " + sheep + ", wolf = " + wolf);

        for (int next : graph[node]){
            if(info[next] == 2){
                int nextSheep = sheep, nextWolf = wolf;
                if(v[next][nextSheep][nextWolf] || nextSheep <= nextWolf){
                    continue;
                }
				v[next][nextSheep][nextWolf] = true;
                DFS(info, next, nextSheep, nextWolf);
                v[next][nextSheep][nextWolf] = false;
                continue;
            }
            if (info[next] == 1){
                int nextSheep = sheep, nextWolf = wolf + 1;
                if(v[next][nextSheep][nextWolf] || nextSheep <= nextWolf){
                    continue;
                }
				v[next][nextSheep][nextWolf] = true;
                info[next] = 2;
                DFS(info, next, nextSheep, nextWolf);
                v[next][nextSheep][nextWolf] = false;
                info[next] = 1;
                continue;
            }

            info[next] = 2;
            int nextSheep = sheep + 1, nextWolf = wolf;
            if(v[next][nextSheep][nextWolf] || nextSheep <= nextWolf){
                continue;
            }
            v[next][nextSheep][nextWolf] = true;
            DFS(info, next, nextSheep, nextWolf);
            v[next][nextSheep][nextWolf] = false;
            info[next] = 0;
        }
    }

}