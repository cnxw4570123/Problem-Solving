import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    //    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int MOD = 1_000;
    static int N;
    static long B;
    static int[][] matrix, ANS;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseUnsignedInt(st.nextToken());
        B = Long.parseUnsignedLong(st.nextToken());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseUnsignedInt(st.nextToken());
            }
        }
        ANS = DNC(B);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(ANS[i][j] % MOD + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int[][] DNC(long n) {
        if (n == 1) {
            return matrix;
        }

        int[][] newMatrix = DNC(n / 2);
        if (n % 2 == 0) {
            return multiply(newMatrix, newMatrix);
        }
        // m = n / 2, n = m + m + 1
        int[][] nextMatrix = multiply(matrix, newMatrix);
        return multiply(newMatrix, nextMatrix);
    }

    static int[][] multiply(final int[][] base, final int[][] input) {
        int[][] newMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    newMatrix[i][j] = (newMatrix[i][j] + (base[i][k] * input[k][j])) % MOD;
                }
            }
        }
        return newMatrix;
    }

}