import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Commuting implements Comparable<Commuting> {

        int start;
        int end;

        public Commuting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Commuting c) {
            if (end == c.end) {
                return start - c.start;
            }
            return end - c.end;
        }

    }

    static Commuting[] lines;
    static PriorityQueue<Integer> area = new PriorityQueue<>();
    static int N, D;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lines = new Commuting[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int min = Integer.parseInt(st.nextToken());
            int max = Integer.parseInt(st.nextToken());
            if (min > max) {
                int tmp = min;
                min = max;
                max = tmp;
            }
            lines[i] = new Commuting(min, max);
        }
        Arrays.sort(lines);
        D = Integer.parseInt(br.readLine());
        bw.write(solve() + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static int solve() {
        int count = 0;
        int idx = 0;
        int end;
        for (int i = 0; i < N; i++) {
            area.add(lines[i].start);
            while (!area.isEmpty() && area.peek() < lines[i].end - D) {
                area.poll();
            }
            count = Math.max(count, area.size());
        }
        return count;
    }


}