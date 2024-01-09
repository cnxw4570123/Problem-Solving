import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][][] LCS;

    static String str1, str2, str3;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        str3 = br.readLine();
        int N = str1.length(), M = str2.length(), O = str3.length();
        LCS = new int[N + 1][M + 1][O + 1];

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M + 1; j++) {
                for (int k = 0; k < O + 1; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        continue;
                    }
                    if (str1.charAt(i - 1) == str2.charAt(j - 1) && str2.charAt(j - 1) == str3.charAt(k - 1)) {
                        LCS[i][j][k] = LCS[i - 1][j - 1][k - 1] + 1;
                    } else {
                        LCS[i][j][k] = Math.max(Math.max(LCS[i - 1][j][k], LCS[i][j - 1][k]), LCS[i][j][k - 1]);
                    }
                }
            }
        }
        bw.write(LCS[N][M][O] + "");
        bw.flush();
        bw.close();
        br.close();
    }

}