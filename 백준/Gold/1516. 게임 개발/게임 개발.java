import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] inDegree, cost, ans;
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static Queue<Integer> q = new ArrayDeque<>();
    static int N;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inDegree = new int[N + 1];
        cost = new int[N + 1];
        ans = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int parent = Integer.parseInt(st.nextToken());
                if (parent == -1) {
                    break;
                }
                List<Integer> subGraph = graph.getOrDefault(parent, new ArrayList<>());
                subGraph.add(i);
                inDegree[i]++;
                graph.put(parent, subGraph);
            }
        }

        for (int i = 1; i < N + 1; i++) {
            ans[i] = cost[i];
            if (inDegree[i] != 0) {
                continue;
            }
            q.add(i);
        }
        topologySort();
        for (int i = 1; i < N + 1; i++) {
            bw.write(ans[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void topologySort() {
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    q.add(next);
                }
                ans[next] = Math.max(ans[next], cost[next] + ans[cur]);
            }
        }
    }

}