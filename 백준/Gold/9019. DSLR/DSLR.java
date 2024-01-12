import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {
    static final int MAX = 10_000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T, A, B;
    static boolean[] v = new boolean[MAX + 1];

    static class Move {
        int now;
        List<Character> seq;

        public Move(int now, List<Character> seq) {
            this.now = now;
            this.seq = seq;
        }

        public Move(int now, List<Character> seq, char op) {
            this.now = now;
            this.seq = new ArrayList<>(seq);
            this.seq.add(op);
        }
    }

    static enum Operation {
        D('D', (num) -> num * 2 % MAX),
        S('S', (num) -> num == 0 ? 9999 : num - 1),
        L('L', (num) -> {
            int leftMostDigit = num / 1000;
            return (num % 1000) * 10 + leftMostDigit;
        }),
        R('R', (num) -> {
            int rightMostDigit = num % 10;
            return rightMostDigit * 1000 + num / 10;

        });


        char op;
        Function<Integer, Integer> function;

        Operation(char op, Function<Integer, Integer> function) {
            this.op = op;
            this.function = function;
        }
    }

    static Queue<Move> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            BFS();
            q.clear();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void BFS() throws IOException {
        Arrays.fill(v, false);
        q.add(new Move(A, new ArrayList<>()));

        while (!q.isEmpty()) {
            Move current = q.poll();
            if (v[current.now]) {
                continue;
            }
            v[current.now] = true;

            if (current.now == B) {
                for (char c : current.seq) {
                    bw.write(c + "");
                }
                bw.write("\n");
                return;
            }
            for (Operation operation : Operation.values()) {
                int next = operation.function.apply(current.now);
                if (next < 0 || next > MAX || v[next]) {
                    continue;
                }
                q.add(new Move(next, current.seq, operation.op));
            }
        }
    }

}