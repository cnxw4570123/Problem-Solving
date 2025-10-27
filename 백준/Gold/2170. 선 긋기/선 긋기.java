import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[][] lines;
    static int MIN = -1_000_000_001;

    public static void main(String[] args) throws IOException, InterruptedException {
        N = Integer.parseInt(br.readLine());
        lines = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(lines, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) {
                return arr1[1] - arr2[1];
            }
            return arr1[0] - arr2[0];
        });

        int start = MIN, end = MIN, length = 0;

        for (int[] line : lines) {
            if (start < line[0] && end < line[0]) {
                start = line[0];
                end = line[1];
                length += end - start;
                continue;
            }
            if (start < line[0] && end < line[1]) {
                length += line[1] - end;
                end = line[1];
                continue;
            }
        }
        bw.write(length + "");

        bw.flush();
        bw.close();
        br.close();
    }

}