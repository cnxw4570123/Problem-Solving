import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static final int MAX = 100_001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static boolean[] v = new boolean[MAX];

    static int[] prev = new int[MAX];

    static class Move {
        int now;
        int time;

        public Move(int now, int time) {
            this.now = now;
            this.time = time;
        }

    }

    static Stack<Integer> s = new Stack<>();
    static Queue<Move> q = new ArrayDeque<>();
    static int N, K;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        prev[N] = N;
        q.add(new Move(N, 0));
        BFS();
        int idx = prev[K];
        s.add(K);
        while (idx != N) {
            s.add(idx);
            idx = prev[idx];
        }
        if (N != K) {
            s.add(N);
        }
        while (!s.isEmpty()) {
            bw.write(s.pop() + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void BFS() throws IOException {
        while (!q.isEmpty()) {
            Move current = q.poll();
            if (current.now == K) {
                bw.write(current.time + "\n");
                return;
            }

            for (int next : new int[]{current.now - 1, current.now + 1, current.now * 2}) {
                if (next < 0 || next >= MAX || v[next]) {
                    continue;
                }
                v[next] = true;
                q.add(new Move(next, current.time + 1));
                prev[next] = current.now;
            }
        }
    }

}