import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static List<Integer> lis = new ArrayList<>();
    static int[] arr, indexes;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        indexes = new int[N];
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            if (lis.isEmpty() || lis.get(lis.size() - 1) < num) {
                lis.add(num);
                indexes[i] = lis.size() - 1;
            } else {
                int index = binarySearch(num);
                indexes[i] = index;
                lis.set(index, num);
            }
        }

        Stack<Integer> s = new Stack<>();
        int i = lis.size() - 1;
        for (int j = indexes.length - 1; j >= 0; j--) {
            if (indexes[j] == i) {
                s.push(arr[j]);
                i--;
            }
        }
        bw.write(lis.size()+"\n");
        while (!s.isEmpty()) {
            bw.write(s.pop() + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int binarySearch(int value) {
        int start = 0, end = lis.size();
        while (start < end) {
            int mid = (start + end) / 2;
            if (lis.get(mid) < value) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }

}