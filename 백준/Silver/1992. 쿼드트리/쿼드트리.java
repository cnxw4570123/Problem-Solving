import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[][] quadTree;

    public static void main(String[] args) throws IOException {
        N = Integer.parseUnsignedInt(br.readLine());
        quadTree = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                quadTree[i][j] = s.charAt(j) - '0';
            }
        }
        printQuadTree(N, 0, 0);
        bw.flush();
        bw.close();
        br.close();
    }

    static void printQuadTree(int size, int y, int x) throws IOException {
        if (canZip(size, y, x)) {
            bw.write(quadTree[y][x] + "");
            return;
        }
        bw.write("(");
        printQuadTree(size / 2, y, x);
        printQuadTree(size / 2, y, x + size / 2);
        printQuadTree(size / 2, y + size / 2, x);
        printQuadTree(size / 2, y + size / 2, x + size / 2);
        bw.write(")");
    }

    static boolean canZip(int size, int y, int x) {
        int criteria = quadTree[y][x];
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (criteria != quadTree[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}