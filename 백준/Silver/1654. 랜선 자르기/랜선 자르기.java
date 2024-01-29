import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int K, N;
    static long[] arr;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new long[K];
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        bw.write(binarySearch(N) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static long binarySearch(int target) {
        long start = 1, end = arr[K - 1];
        while (start <= end) {
            long count = 0, mid = (start + end) / 2;

            for (long cable : arr) {
                count += cable / mid;
            }

            if (count < target) {
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return end;
    }

}