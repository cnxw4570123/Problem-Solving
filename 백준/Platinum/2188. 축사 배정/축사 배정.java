import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] aMatch, bMatch;
    static boolean[] visited;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            while (st.hasMoreTokens()) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        bw.write(bipartiteMatch() + "");

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

    static int bipartiteMatch() {
        aMatch = new int[N + 1];
        bMatch = new int[M + 1];
        int size = 0;
        for (int start = 1; start < N + 1; start++) {
            visited = new boolean[N + 1];
            if (DFS(start)) {
                size++;
            }
        }
        return size;
    }
}