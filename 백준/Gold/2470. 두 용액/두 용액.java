import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] arr;
    static int N;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int start = 0, end = N - 1, sum = Integer.MAX_VALUE;
        int[] elems = {0, 0};

        while (start < end) {
            if (Math.abs(arr[start] + arr[end]) < Math.abs(sum)) {
                sum = arr[start] + arr[end];
                elems[0] = arr[start];
                elems[1] = arr[end];

            }

            if (arr[start] + arr[end] < 0) {
                start++;
            } else {
                end--;
            }

        }
        bw.write(elems[0] + " " + elems[1]);
        bw.flush();
        bw.close();
        br.close();
    }

}