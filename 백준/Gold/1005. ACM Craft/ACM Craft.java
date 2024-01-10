import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] time, ans, inDegree;
    static int T, N, K, W;
    static List<Integer>[] graph;
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            inDegree = new int[N + 1];
            ans = new int[N + 1];
            time = new int[N + 1];
            graph = new List[N + 1];
            for (int i = 0; i < N + 1; i++) {
                graph[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());

            for (int i = 1; i < N + 1; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
                graph[x].add(y);
                inDegree[y]++;
            }
            for (int i = 1; i < N + 1; i++) {
                ans[i] = time[i];
                if (inDegree[i] == 0) {
                    q.add(i);
                }
            }
            W = Integer.parseInt(br.readLine());
            bw.write(topologySort(W) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int topologySort(int w) {
        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph[current]) {
                inDegree[next]--;
                ans[next] = Math.max(ans[next], ans[current] + time[next]);
                if (inDegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        return ans[w];
    }
}