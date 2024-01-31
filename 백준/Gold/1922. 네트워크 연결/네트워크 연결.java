import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static class Edge implements Comparable<Edge>{
        int start;
        int to;
        int cost;

        public Edge(int start, int to, int cost) {
            this.start = start;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return cost - e.cost;
        }
    }
    static int N, M, ans = 0;
    static int[] parent;
    static Edge[] edges;
    static boolean check;
    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        edges = new Edge[M];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()),
                    c = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }
        Arrays.sort(edges);

        for (Edge edge : edges) {
            union(edge.start, edge.to);
            if (check) {
                ans += edge.cost;
            }
        }
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);
        check = false;
        if(x == y){
            return;
        }
        check = true;
        if (x < y) {
            parent[x] = y;
        } else{
            parent[y] = x;
        }
    }
}