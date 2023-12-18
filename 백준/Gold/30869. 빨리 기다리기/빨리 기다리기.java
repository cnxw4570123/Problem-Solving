import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static List<Route>[] graph;
    static Dist[] dists;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();

        dijkstra();
        if (dists[N].dist == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(dists[N].dist + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseUnsignedInt(st.nextToken());
        M = Integer.parseUnsignedInt(st.nextToken());
        K = Integer.parseUnsignedInt(st.nextToken());
        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()),
                    t = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken());
            graph[s].add(new Route(e, t, g, K));
        }
        dists = new Dist[N + 1];
        for (int i = 0; i < N + 1; i++) {
            dists[i] = new Dist(Integer.MAX_VALUE, K);
        }
    }

    public static void dijkstra() {
        PriorityQueue<Route> pq = new PriorityQueue<>();
        pq.add(new Route(1, 0, 0, K));

        while (!pq.isEmpty()) {
            Route current = pq.poll();
            if (current.to == N) {
                continue;
            }

            for (Route next : graph[current.to]) {
                int waiting = (next.dispatch - (current.cost % next.dispatch)) % next.dispatch;
                int costSumWaiting = next.cost + waiting + current.cost;
                if (costSumWaiting < dists[next.to].dist) {
                    dists[next.to] = new Dist(costSumWaiting, current.fastLeft);
                    pq.add(new Route(next.to, costSumWaiting, current.fastLeft));
                }
                if (waiting > 0 && current.fastLeft > 0 && dists[next.to].dist > costSumWaiting - waiting) {
                    dists[next.to] = new Dist(costSumWaiting - waiting, current.fastLeft - 1);
                    pq.add(new Route(next.to, costSumWaiting - waiting, current.fastLeft - 1));
                }
            }
        }
    }

}

class Route implements Comparable<Route> {
    int to;
    int cost;
    int dispatch;
    int fastLeft;

    public Route(int to, int cost, int repeat, int fastLeft) {
        this.to = to;
        this.cost = cost;
        this.dispatch = repeat;
        this.fastLeft = fastLeft;
    }

    public Route(int to, int cost, int fastLeft) {
        this.to = to;
        this.cost = cost;
        this.fastLeft = fastLeft;
    }

    @Override
    public int compareTo(Route o) {
        return cost - o.cost;
    }

    @Override
    public String toString() {
        return "Route{" +
                "to=" + to +
                ", cost=" + cost +
                ", repeat=" + dispatch +
                ", fastLeft=" + fastLeft +
                '}';
    }
}

class Dist {
    int dist;
    int FastLeft;

    public Dist(int dist, int fastLeft) {
        this.dist = dist;
        FastLeft = fastLeft;
    }

    @Override
    public String toString() {
        return "Dist{" +
                "dist=" + dist +
                ", FastLeft=" + FastLeft +
                '}';
    }
}