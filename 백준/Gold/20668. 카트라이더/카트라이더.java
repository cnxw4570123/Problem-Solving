import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final long INF = Long.MAX_VALUE;
    static final long MULTIPLY = 3628800L;

    static class Edge {
        int to, length, constraint;

        public Edge(int to, int length, int constraint) {
            this.to = to;
            this.length = length;
            this.constraint = constraint;
        }
    }

    static class Route {
        int to, velocity;
        long time;

        public Route(int to, long time, int velocity) {
            this.to = to;
            this.time = time;
            this.velocity = velocity;
        }
    }

    static long[][] dist;
    static List<Edge>[] graph;
    static int N, M;
    static long ans = INF;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new long[11][N + 1];
        for (long[] longs : dist) {
            Arrays.fill(longs, Long.MAX_VALUE);
        }

        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        int a = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, l, k));
            graph[b].add(new Edge(a, l, k));
        }
        dijkstra();

        for (int i = 1; i < 11; i++) {
            ans = Math.min(ans, dist[i][N]);
        }
        BigDecimal bigDecimal = new BigDecimal(ans);
        BigDecimal div1 = new BigDecimal(MULTIPLY);
        BigDecimal divide = bigDecimal.divide(div1, 9, RoundingMode.HALF_UP);
        bw.write(divide.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    static long round(long target){
        if(target % 10 >= 5){
            return target / 10 + 1;
        }
        return target / 10;
    }
    static void dijkstra() {
        PriorityQueue<Route> pq = new PriorityQueue<>(Comparator.comparingLong(route -> route.time));
        pq.add(new Route(1, 0, 1));
        dist[1][1] = 0;
        while (!pq.isEmpty()) {
            Route current = pq.poll();
            if (current.time > dist[current.velocity][current.to] || current.to == N) {
                continue;
            }

            for (Edge next : graph[current.to]) {
                for (int nv : new int[]{current.velocity - 1, current.velocity, current.velocity + 1}) {
                    if (nv > next.constraint || nv < 1) {
                        continue;
                    }
                    long cost = (next.length * MULTIPLY) / nv;
                    long nextTime = current.time + cost;
                    if (nextTime >= dist[nv][next.to]) {
                        continue;
                    }
                    dist[nv][next.to] = nextTime;
                    pq.add(new Route(next.to, nextTime, nv));

                }
            }
        }
    }
}
