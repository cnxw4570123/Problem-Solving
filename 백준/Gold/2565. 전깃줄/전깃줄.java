import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static final int MAX = 2_000_000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<Integer> A = new ArrayList<>(), B = new ArrayList<>();
    static int N;

    static class Line implements Comparable<Line> {
        int left;
        int right;

        public Line(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Line line) {
            if (line.left == left) {
                return right - line.right;
            }
            return left - line.left;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    static Line[] arr;

    static List<Line> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Line[N];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            arr[i] = new Line(left, right);
        }
        Arrays.sort(arr);

        for (Line line : arr) {
            if (lines.isEmpty()
                    || lines.get(lines.size() - 1).left < line.left && lines.get(lines.size() - 1).right < line.right) {
                lines.add(line);
            } else {
                lines.set(lowerBound(line), line);
            }
        }
//        bw.write(lines + "\n");
        bw.write(N - lines.size() + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static int lowerBound(Line target) {
        int start = 0, end = lines.size();
        while (start < end) {
            int mid = (start + end) >> 1;
            Line line = lines.get(mid);
            if (line.left < target.left && line.right < target.right) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }

}