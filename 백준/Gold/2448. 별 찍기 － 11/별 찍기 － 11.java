import java.io.*;
import java.util.Arrays;


public class Main {
    static int N, length, start;
    static char[][] triangle;
    static final char STAR = '*';
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        length = N * 2 - 1;
        start = length / 2;
        triangle = new char[N][length];
        for (int i = 0; i < N; i++) {
            Arrays.fill(triangle[i], ' ');
        }
        printStar(N, 0, start);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < length; j++) {
                bw.write(triangle[i][j]);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // 1 4 7 11 ~~
    static void printStar(int n, int y, int x) {
        if (n == 3) {
            triangle[y][x] = STAR;
            triangle[y + 1][x - 1] = triangle[y + 1][x + 1] = STAR;
            for (int i = x - 2; i < x + 3; i++) {
                triangle[y + 2][i] = STAR;
            }

        } else {
            printStar(n / 2, y, x);
            printStar(n / 2, y + n / 2, x - n / 2);
            printStar(n / 2, y + n / 2, x + n / 2);
        }
    }
}