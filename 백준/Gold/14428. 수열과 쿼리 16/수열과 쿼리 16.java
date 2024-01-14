import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100_001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class SegmentTree {
        int[] tree;

        SegmentTree(int n) {
            double height = Math.ceil(Math.log(n) / Math.log(2)) + 1;
            int count = Math.toIntExact(Math.round(Math.pow(2, height)));
            tree = new int[count];
        }

        int init(int[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = start;
            }
            int mid = (start + end) / 2;
            int leftChild = init(arr, node * 2, start, mid);
            int rightChild = init(arr, node * 2 + 1, mid + 1, end);
            if(arr[leftChild] == arr[rightChild]){
                return tree[node] = Math.min(leftChild, rightChild);
            }
            else if (arr[leftChild] < arr[rightChild]) {
                return tree[node] = leftChild;
            }
            return tree[node] = rightChild;
        }

        int query(int[] arr, int node, int start, int end, int left, int right) {
            if (start > right || end < left) {
                return 0;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            int leftMinIdx = query(arr, node * 2, start, mid, left, right);
            int rightMinIdx = query(arr, node * 2 + 1, mid + 1, end, left, right);

            if (arr[leftMinIdx] == arr[rightMinIdx]) {
                return Math.min(leftMinIdx, rightMinIdx);
            } else if (arr[leftMinIdx] < arr[rightMinIdx]) {
                return leftMinIdx;
            }
            return rightMinIdx;
        }

        int update(int[] arr, int node, int start, int end, int idx) {
            if (end < idx || start > idx || start == end) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            int leftMinIdx = update(arr, node * 2, start, mid, idx);
            int rightMinIdx = update(arr, node * 2 + 1, mid + 1, end, idx);
            if(arr[leftMinIdx] == arr[rightMinIdx]){
                return tree[node] = Math.min(leftMinIdx, rightMinIdx);
            }
            if (arr[leftMinIdx] < arr[rightMinIdx]) {
                return tree[node] = leftMinIdx;
            }
            return tree[node] = rightMinIdx;
        }

    }

    static int N, M;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[0] = Integer.MAX_VALUE;

        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(arr, 1, 1, N);

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()),
                    v = Integer.parseInt(st.nextToken());
            if (a == 1) {
                arr[b] = v;
                segmentTree.update(arr, 1, 1, N, b);
            } else {
                bw.write(segmentTree.query(arr, 1, 1, N, b, v) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}