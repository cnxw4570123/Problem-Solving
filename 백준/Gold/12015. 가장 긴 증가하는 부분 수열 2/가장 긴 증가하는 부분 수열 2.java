import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] LIS, arr;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        LIS = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(LIS, Integer.MAX_VALUE);
        LIS[1] = arr[1];

        int ans = 1;
        for (int i = 2; i < N + 1; i++) {
            int index = binarySearch(arr[i]);
            LIS[index] = arr[i];
            ans = Math.max(index, ans);
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static int binarySearch(int target){
        int start = 1, end = N;

        while(start < end){
            int mid = (start + end) / 2;
            if(LIS[mid] < target){
                start = mid + 1;
            } else{
                end = mid;
            }
        }
        return end;
    }

}