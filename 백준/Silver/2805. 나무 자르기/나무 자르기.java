import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        bw.write(upperBound() - 1 + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static long upperBound() {
        long start = 0, end = arr[arr.length - 1];
        while (start < end) {
            long mid = (start + end) / 2;
            long sum = 0;
            for (int tree : arr) {
                if (tree <= mid) {
                    continue;
                }
                sum += tree - mid;
            }

            if (sum < M) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
}