import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        bw.write(upperBound() - 1 + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int upperBound() {
        int start = 0, end = arr[arr.length - 1] + 1;
        while (start < end) {
            int mid = (start + end) / 2;
            int count = 1, prev = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] - prev >= mid) {
                    count++;
                    prev = arr[i];
                }
            }

            if (C <= count) {
                start = mid + 1;
            } else {
                end = mid;
            }

        }
        return end;
    }
}