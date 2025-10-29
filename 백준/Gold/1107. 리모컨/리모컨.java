import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, ans;
    static Set<Integer> brokenButtons = new HashSet<>(), candidates = new HashSet<>();
    static List<Integer> buttons = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                brokenButtons.add(Integer.parseInt(st.nextToken()));
            }
        }

        ans = Math.abs(N - 100);
        for (int i = 0; i < 1_000_001; i++) {
            if (!isPossible(i)) {
                continue;
            }
            ans = Math.min(ans, Math.abs(N - i) + calculateLength(i));
        }
        bw.write(ans + "");

        bw.flush();
        bw.close();
        br.close();
    }

    static boolean isPossible(int target) {
        int len = calculateLength(target);
        while (len-- > 0) {
            if (brokenButtons.contains(target % 10)) {
                return false;
            }
            target /= 10;
        }
        return true;
    }

    static int calculateLength(int target) {
        int base = 1;
        for (int i = 0; i < 6; i++) {
            base *= 10;
            if (target < base) {
                return i + 1;
            }
        }
        return -1;
    }
}