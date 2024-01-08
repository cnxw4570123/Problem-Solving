import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K;

    static class Jewelry {
        int weight;
        int value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    static PriorityQueue<Jewelry> pqByWeight = new PriorityQueue<>((j1, j2) -> j1.weight - j2.weight);
    static PriorityQueue<Jewelry> pqByValue = new PriorityQueue<>((j1, j2) -> j2.value - j1.value);
    static int[] bagSize;
    static long ans;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bagSize = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            pqByWeight.add(new Jewelry(m, v));
        }
        for (int i = 0; i < K; i++) {
            bagSize[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bagSize);

        int idx = 0;
        while (idx < K) {
            while (!pqByWeight.isEmpty() && pqByWeight.peek().weight <= bagSize[idx]) {
                pqByValue.add(pqByWeight.poll());
            }
            Jewelry poll = pqByValue.poll();
            if (poll != null) {
                ans += poll.value;
            }
            idx++;
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }


}