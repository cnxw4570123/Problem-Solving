import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T, N, M;
    static int[] aMatch, bMatch;
    static boolean[] visited;
    static Set<Integer>[] graph;
    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new Set[M + 1];
            for (int i = 1; i < M + 1; i++) {
                graph[i] = new TreeSet<>();
            }
            for (int i = 1; i < M + 1; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
                for (int j = start; j <= end; j++) {
                    graph[i].add(j);
                }
            }
            bw.write(bipartiteMatching() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean DFS(int a) {
        if (visited[a]) {
            return false;
        }
        visited[a] = true;

        for (int b : graph[a]) {
            if (bMatch[b] == 0 || DFS(bMatch[b])) {
                aMatch[a] = b;
                bMatch[b] = a;
                return true;
            }
        }
        return false;
    }

    static int bipartiteMatching() {
        aMatch = new int[M + 1];
        bMatch = new int[N + 1];
        int size = 0;

        for (int i = 1; i < M + 1; i++) {
            visited = new boolean[M + 1];
            if(DFS(i)){
                size++;
            }
        }
        return size;
    }
}