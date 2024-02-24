import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static class Number{
        int idx;
        int value;

        public Number(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

    }

    static ArrayDeque<Number> dq = new ArrayDeque<>();
    static int N, L;
    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            while (!dq.isEmpty() && a < dq.peekLast().value) {
                dq.pollLast();
            }
            dq.add(new Number(i, a));
            if (dq.peekFirst().idx <= i - L) {
                dq.pollFirst();
            }
            bw.write(dq.peekFirst().value + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}