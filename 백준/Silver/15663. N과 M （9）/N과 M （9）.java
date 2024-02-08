import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static final String CAN_NOT_ESCAPE = "KAKTUS";
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[] arr;
    static Set<String> treeSet = new TreeSet<>();
    static boolean[] v;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[N];
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        DFS(0, "");
        treeSet.stream().sorted((o1, o2) -> {
            StringTokenizer st1 = new StringTokenizer(o1);
            StringTokenizer st2 = new StringTokenizer(o2);
            while (st1.hasMoreTokens() && st2.hasMoreTokens()) {
                int a = Integer.parseInt(st1.nextToken());
                int b = Integer.parseInt(st2.nextToken());
                if(a == b){
                    continue;
                }
                return Integer.compare(a,b);
            }
            return 0;
        }).forEach(s -> {
            try {
                bw.write(s.trim() +"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bw.flush();
        bw.close();
        br.close();
    }

    static void DFS(int count, String res) {
        if (count == M) {
            treeSet.add(res);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (v[i]) {
                continue;
            }
            v[i] = true;
            DFS(count + 1, res + " "+ arr[i]);
            v[i] = false;
        }
    }
}