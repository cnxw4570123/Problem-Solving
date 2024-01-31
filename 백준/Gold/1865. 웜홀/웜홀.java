import java.io.*;
import java.util.*;


public class Main {
    //    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static class Edge {
        int from;
        int to;
        long cost;

        public Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    static final int MAX = 501;
    static List<Edge> graph = new ArrayList<>();
    static Set<Integer> nodes = new HashSet<>();
    static long[] dist = new long[MAX];
    static int TC, N, M, W;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            boolean next = false;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
                changeIfContains(new Edge(s, e, t));
                changeIfContains(new Edge(e, s, t));
                nodes.add(s);
                nodes.add(e);
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
                changeIfContains(new Edge(s, e, -t));
                nodes.add(s);
                nodes.add(e);
            }
            if(bellmanFord()){
                bw.write("YES\n");
            } else{
                bw.write("NO\n");
            }
            graph.clear();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean bellmanFord() {
        for (int i = 1; i < N + 1; i++) {
            for (Edge edge : graph) {
                if (dist[edge.from] == Long.MAX_VALUE || dist[edge.to] <= dist[edge.from] + edge.cost) {
                    continue;
                }
                dist[edge.to] = dist[edge.from] + edge.cost;
            }
        }

        for (Edge edge : graph) {
            if (dist[edge.from] == Long.MAX_VALUE || dist[edge.to] <= dist[edge.from] + edge.cost) {
                continue;
            }
            return true;
        }
        return false;
    }

    static void changeIfContains(Edge edge) {
        int index = graph.indexOf(edge);
        if (index == -1) {
            graph.add(edge);
            return;
        }
        Edge sub = graph.get(index);
        if (sub.cost > edge.cost) {
            sub.cost = edge.cost;
        }
    }

}