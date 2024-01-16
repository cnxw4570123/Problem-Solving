import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] parent;
    static boolean hasCycle;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            union(a, b);
            if(hasCycle){
                bw.write(i + "\n");
                bw.flush();
                bw.close();
                br.close();
                return;
            }
        }
        bw.write("0");
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

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        hasCycle = false;
        if (x == y) {
            hasCycle = true;
            return;
        }

        if (parent[x] < parent[y]) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }


}