import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static BiFunction<Pos, Pos, Integer> getDist = (pos, pos2) -> Math.abs(pos.y - pos2.y) + Math.abs(pos.x - pos2.x);
    static int[][] map;
    static int N, M, minDist = Integer.MAX_VALUE;
    static Pos[] survived;
    static boolean[] v;
    static List<Pos> chicken = new ArrayList<>(), houses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    houses.add(new Pos(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Pos(i, j));
                }
            }
        }

        v = new boolean[chicken.size()];
        survived = new Pos[M];
        getMinDist(0, 0);
        bw.write(minDist + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void getMinDist(int count, int idx) {
        if (count == M) {
            int sum = 0;
            for (Pos h : houses) {
                int min = Integer.MAX_VALUE;
                for(Pos c : survived){
                    min = Math.min(min, getDist.apply(c, h));
                }
                sum += min;
            }
            minDist = Math.min(sum, minDist);
            return;
        }
        for (int i = idx; i < chicken.size(); i++) {
            if (v[i]) {
                continue;
            }
            v[i] = true;
            survived[count] = chicken.get(i);
            getMinDist(count + 1, i);
            v[i] = false;
        }
    }
}