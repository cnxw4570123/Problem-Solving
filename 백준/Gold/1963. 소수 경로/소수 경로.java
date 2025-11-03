import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException, InterruptedException {
        T = Integer.parseInt(br.readLine());
        findPrimeNumber();
        StringBuilder ans = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            ans.append(BFS(A, B)).append("\n");
        }
        bw.write(ans.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void findPrimeNumber() {
        isPrime = new boolean[10_001];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i < Math.sqrt(10_001) + 1; i++) {
            for (int j = i * 2; j < 10_001; j += i) {
                if (!isPrime[j]) {
                    continue;
                }
                isPrime[j] = false;
            }
        }
    }

    static String BFS(int start, int end) {
        boolean[] v = new boolean[10_001];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start, 0});
        v[start] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            if (current[0] == end) {
                return current[1] + "";
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    int next = move(current[0], i, j);
                    if (next < 1000 || v[next] || !isPrime[next]) {
                        continue;
                    }
                    v[next] = true;
                    q.add(new int[]{next, current[1] + 1});
                }

            }
        }
        return "Impossible";
    }

    static int move(int target, int digit, int number) {
        int div = target / (int) (Math.pow(10, digit + 1));
        int mod = target % (int) (Math.pow(10, digit));

        return (int) (div * (Math.pow(10, digit + 1)) + number * Math.pow(10, digit) + mod);

    }
}