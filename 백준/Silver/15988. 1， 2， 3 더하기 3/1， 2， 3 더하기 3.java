import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T, MOD = 1_000_000_009;
    static long[] dp;
    static int[] numbers;

    public static void main(String[] args) throws IOException, InterruptedException {
        T = Integer.parseInt(br.readLine());
        numbers = new int[T];

        int max = 0;
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            max = Math.max(n, max);
            numbers[i] = n;
        }

        dp = new long[max + 1];
        dp[0] = 1;

        for (int i = 1; i < max + 1; i++) {
            long res = 0;
            for (int j = 1; j < 4; j++) {
                if (i < j) {
                    continue;
                }
                res = (res + dp[i - j]) % MOD;
            }
            dp[i] = res % MOD;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            sb.append(dp[numbers[i]]).append("\n");
        }
        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

}