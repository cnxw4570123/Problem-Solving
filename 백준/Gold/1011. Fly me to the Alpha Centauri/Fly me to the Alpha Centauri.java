import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int T, X, Y;
    static long[] sum;
    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        sum = new long[131072];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + i;
        }
        while (T-- > 0) {
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            int diff = Y - X - 1, ans = 1, prev = 1;
//            bw.write(diff + 1 + " ");
            sb.append(1);
            while (diff > 0) {
                sb.append("->");
                if (sum[prev + 1] <= diff) {
                    diff -= ++prev;
                } else if (sum[prev] <= diff) {
                    diff -= prev;
                } else{
                    diff -= --prev;
                }
//                sb.append(prev);
                ans++;
            }
//            bw.write(sb.toString() + "\n");

            bw.write(ans +"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}