import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static final int MAX = 10_000_001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] cost;

    static class Path {
        int dist;

        List<Integer> path;

        public Path(int dist, List<Integer> path) {
            this.dist = dist;
            this.path = new ArrayList<>(path);
        }

        public void setPath(int from, int by, int to) {
            this.dist = cost[from][to];
            path.clear();
            path.addAll(minDistPath[from][by].path);
            path.addAll(minDistPath[by][to].path);
        }
    }

    static Path[][] minDistPath;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        cost = new int[N + 1][N + 1];
        minDistPath = new Path[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(cost[i], MAX);
            cost[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()),
                    c = Integer.parseInt(st.nextToken());
            if (cost[a][b] > c) {
                cost[a][b] = c;
                minDistPath[a][b] = new Path(c, new ArrayList<>());
                minDistPath[a][b].path.add(b);
            }
        }
        floydWarshall();
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                bw.write(cost[i][j] == MAX ? "0 " : cost[i][j] + " ");
            }
            bw.write("\n");
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (minDistPath[i][j] == null) {
                    bw.write("0\n");
                    continue;
                }
                bw.write(minDistPath[i][j].path.size() + 1 + " ");
                StringBuilder sb = new StringBuilder(Integer.toString(i));
                for (int num : minDistPath[i][j].path) {
                    sb.append(" ").append(num);
                }
                bw.write(sb.toString() + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void floydWarshall() {
        for (int j = 1; j < N + 1; j++) {
            for (int i = 1; i < N + 1; i++) {
                for (int k = 1; k < N + 1; k++) {
                    if (cost[i][j] + cost[j][k] < cost[i][k]) {
                        cost[i][k] = cost[i][j] + cost[j][k];
                        if (minDistPath[i][k] == null) {
                            minDistPath[i][k] = new Path(0, new ArrayList<>());
                        }
                        minDistPath[i][k].setPath(i, j, k);

                    }
                }
            }
        }
    }
}