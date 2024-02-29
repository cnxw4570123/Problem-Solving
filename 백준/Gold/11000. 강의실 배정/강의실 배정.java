import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;

    static class Pos implements Comparable<Pos> {
        int start, end;

        public Pos(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pos p) {
            if (start == p.start) {
                return end - p.end;
            }
            return start - p.start;
        }
    }

    static Pos[] lectures;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lectures = new Pos[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lectures[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int ans = 0;
        Arrays.sort(lectures);
        for (int i = 0; i < N; i++) {
            while (!pq.isEmpty() && pq.peek() <= lectures[i].start) {
                pq.poll();
            }
            pq.add(lectures[i].end);
            ans = Math.max(ans, pq.size());
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
