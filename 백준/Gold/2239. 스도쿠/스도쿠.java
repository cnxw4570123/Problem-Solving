import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] sudoku = new int[9][9];
    static ArrayDeque<int[]> pos = new ArrayDeque<>();

    static final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                int num = s.charAt(j) - '0';
                if (num == 0) {
                    pos.add(new int[]{i, j});
                }
                sudoku[i][j] = num;
            }
        }
        backTracking();

        bw.flush();
        bw.close();
        br.close();
    }

    static void backTracking() throws IOException {
        if (pos.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    bw.write(sudoku[i][j] + "");
                }
                bw.write("\n");
            }
            bw.flush();
            System.exit(0);
        }
        int[] current = pos.poll();
        Set<Integer> canUse = check(current[0], current[1]);
        for (int num : canUse) {
            sudoku[current[0]][current[1]] = num;
            backTracking();
            sudoku[current[0]][current[1]] = 0;
        }
        pos.addFirst(current);
    }

    static Set<Integer> check(int y, int x) {
        Set<Integer> canUse = new HashSet<>(numbers);
        int sRow = y / 3, sCol = x / 3;
        for (int i = 0; i < 9; i++) {
            canUse.remove(sudoku[y][i]);
            canUse.remove(sudoku[i][x]);
            canUse.remove(sudoku[sRow * 3 + i / 3][sCol * 3 + i % 3]);
        }

        return canUse;
    }
}