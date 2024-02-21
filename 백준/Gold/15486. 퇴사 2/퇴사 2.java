import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] counsel, rewards;
    static long[] dp;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        counsel = new int[N + 1];
        rewards = new int[N + 1];
        dp = new long[N + 2];
        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            counsel[i] = Integer.parseInt(st.nextToken());
            rewards[i] = Integer.parseInt(st.nextToken());
        }

        // 역순으로 세기
        for (int i = N; i > 0; i--) {
            if (i + counsel[i] > N + 1) {
                dp[i] = dp[i + 1];
            } else{
                dp[i] = Math.max(dp[i + 1], dp[i + counsel[i]] + rewards[i]);
            }
        }
        bw.write(dp[1] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

}