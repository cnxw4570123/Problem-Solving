import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static long[] aPlusB, cPlusD;
    static int N;
    static long ans;

    static int[] a, b, c, d;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = new int[N];
        b = new int[N];
        c = new int[N];
        d = new int[N];
        aPlusB = new long[N * N];
        cPlusD = new long[N * N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                aPlusB[idx] = a[i] + b[j];
                cPlusD[idx++] = c[i] + d[j];
            }
        }
        Arrays.sort(aPlusB);
        Arrays.sort(cPlusD);

        int left = 0, right = N * N - 1;
        while (left < N * N && right >= 0) {
            if (aPlusB[left] + cPlusD[right] == 0) {
                int originLeft = left;
                long leftCount = 0, rightCount = 0;
                while (left < N * N && aPlusB[left] + cPlusD[right] == 0) {
                    left++;
                    leftCount++;
                }

                while (right >= 0 && aPlusB[originLeft] + cPlusD[right] == 0) {
                    right--;
                    rightCount++;
                }
                ans += leftCount * rightCount;
            } else if (aPlusB[left] + cPlusD[right] > 0) {
                right--;
            } else {
                left++;
            }
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }

}