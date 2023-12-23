import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class SegmentTree {
        long[] tree;

        SegmentTree(int n) {
            double height = Math.ceil(Math.log(n) / Math.log(2)) + 1;
            int count = Math.toIntExact(Math.round(Math.pow(2, height)));
            tree = new long[count];
        }

        public long init(long[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start] % MOD;
            }
            int mid = (start + end) / 2;
            return (tree[node] = init(arr, node * 2, start, mid) % MOD * init(arr, node * 2 + 1, mid + 1, end) % MOD) % MOD;
        }

        public long update(int node, int start, int end, int index, long change) {
            if (index < start || end < index) {
                return tree[node];
            }
            if (start != end) {
                int mid = (start + end) / 2;
                long left = update(node * 2, start, mid, index, change) % MOD;
                long right = update(node * 2 + 1, mid + 1, end, index, change) % MOD;
                return tree[node] = left * right % MOD;
            }
            return tree[node] = change % MOD;
        }

        public long multiply(int node, int start, int end, int left, int right) {
            if (right < start || left > end) {
                return 1;
            }
            if (left <= start && end <= right) {
                return tree[node] % MOD;
            }
            int mid = (start + end) / 2;
            long leftChild = multiply(node * 2, start, mid, left, right) % MOD;
            long rightChild = multiply(node * 2 + 1, mid + 1, end, left, right) % MOD;
            return leftChild * rightChild % MOD;
        }
    }

    static final int MOD = 1_000_000_007;
    static long[] arr;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(arr, 1, 1, N);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            if (command.equals("1")) {
                segmentTree.update(1, 1, N, b, c);
                arr[b] = c;
            } else {
                bw.write(segmentTree.multiply(1, 1, N, b, c) % MOD + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }


}