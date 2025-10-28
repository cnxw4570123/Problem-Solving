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

        for (int i = 0; i < 10; i++) {
            if (brokenButtons.contains(i)) {
                continue;
            }
            buttons.add(i);
        }

        find(10, 0, 0);
        ans = Math.abs(100 - N);
        for (int temp : candidates) {
            int cnt = Math.abs(N - temp) + Integer.toString(temp).length();
            ans = Math.min(ans, cnt);
        }
        bw.write(ans + "");

        bw.flush();
        bw.close();
        br.close();
    }

    static void find(int max_cnt, int cnt, int start) {
        if (cnt == max_cnt || Math.abs(N - start) > 500_000) {
            return;
        }
        if (start != 0 || cnt != 0) {
            candidates.add(start);
        }

        for (int button : buttons) {

            int res = start * 10 + button;
            if (candidates.contains(res)) {
                continue;
            }
            find(max_cnt, cnt + 1, res);
        }
    }
}