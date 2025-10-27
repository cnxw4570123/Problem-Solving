import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static PriorityQueue<Line> lines;
    static int MIN = -1_000_000_001;

    public static void main(String[] args) throws IOException, InterruptedException {
        N = Integer.parseInt(br.readLine());
        lines = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int start = MIN, end = MIN, length = 0;

        while (!lines.isEmpty()) {
            Line line = lines.poll();

            if (start < line.start && end < line.start) {
                start = line.start;
                end = line.end;
                length += end - start;
                continue;
            }
            if (start < line.start && end < line.end) {
                length += line.end - end;
                end = line.end;
            }
        }
        bw.write(length + "");

        bw.flush();
        bw.close();
        br.close();
    }

    static class Line implements Comparable<Line> {

        int start, end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.start, o.start);
        }
    }
}