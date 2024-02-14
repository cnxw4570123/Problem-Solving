import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 110_000;
    static final int ONE_DAY = 10_000, THREE_DAY = 25_000, FIVE_DAY = 37_000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;

    static class Cost {
        int money;
        int coupon;

        public Cost(int money, int coupon) {
            this.money = money;
            this.coupon = coupon;
        }

        @Override
        public String toString() {
            return "Cost{" +
                    "money=" + money +
                    ", coupon=" + coupon +
                    '}';
        }
    }

    static Cost[] dp;
    static boolean[] canNotVisit;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new Cost[N + 1];
        canNotVisit = new boolean[N + 1];
        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                canNotVisit[Integer.parseInt(st.nextToken())] = true;
            }
        }
//        Arrays.fill(dp, new Cost(MAX, 0));
        dp[0] = new Cost(0, 0);

        for (int i = 1; i < N + 1; i++) {
            dp[i] = new Cost(dp[i - 1].money, dp[i - 1].coupon);
            if (!canNotVisit[i]) {
                dp[i].money += ONE_DAY;
            }
            if (dp[i].coupon >= 3) {
                dp[i].money -= ONE_DAY;
                dp[i].coupon -= 3;
            }
            int min = MAX, cp = dp[i].coupon;
            if (i >= 3) {
                if (dp[i - 3].money + THREE_DAY < dp[i].money) {
                    dp[i].money = dp[i - 3].money + THREE_DAY;
                    dp[i].coupon = dp[i - 3].coupon + 1;
                }
            }
            if ((i >= 4) && (dp[i - 4].coupon + 1 >= 3 && dp[i - 4].money + THREE_DAY < dp[i].money)) {
                dp[i].money = dp[i - 4].money + THREE_DAY;
                dp[i].coupon = dp[i - 4].coupon - 2;
            }
            if (i >= 5 && dp[i - 5].money + FIVE_DAY < dp[i].money) {
                dp[i].money = dp[i - 5].money + FIVE_DAY;
                dp[i].coupon = dp[i - 3].coupon + 2;
            }
            if (i >= 6 && (dp[i - 6].coupon + 2 >= 3 && dp[i - 6].money + FIVE_DAY < dp[i].money)) {
                dp[i].money = dp[i - 5].money + FIVE_DAY;
                dp[i].coupon = dp[i - 3].coupon - 1;
            }
        }
        bw.write(dp[N].money + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

}