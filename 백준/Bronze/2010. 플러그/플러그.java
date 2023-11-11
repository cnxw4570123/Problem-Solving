import java.io.*;
import java.util.*;


public class Main {
    static int N, ans = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            ans--;
            int a = Integer.parseInt(br.readLine());
            ans += a;
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}