import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K;
    static long[] indexes = new long[11];

    public static void main(String[] args) throws IOException, InterruptedException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        long prev = 1;
        for (int digit = 1; digit < 11; digit++) {
            long current = (long) Math.pow(10, digit) - 1;
            indexes[digit] = (current - prev + 1) * digit + indexes[digit - 1];
            prev *= 10;
        }

        int sum = 0, temp = N, cnt = 0;
        while (temp != 0) {
            cnt += 1;
            temp /= 10;
        }
        sum += (int) indexes[cnt - 1] + (N - (int) Math.pow(10, cnt - 1) + 1) * cnt;
        if (sum < K) {
            bw.write(-1 + "");
            bw.close();
            br.close();
            return;
        }

        int digits = 0;
        while (indexes[digits] < K) {
            digits++;
        }

        K -= (int) indexes[digits - 1] + 1;
        int start = (int) Math.pow(10, digits - 1) + K / digits;
        bw.write((start + "").charAt(K % digits));
        bw.flush();
        bw.close();
        br.close();
    }

}