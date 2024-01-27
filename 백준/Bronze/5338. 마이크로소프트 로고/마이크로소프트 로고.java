import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw.write("       _.-;;-._\n");
        bw.write("'-..-'|   ||   |\n");
        bw.write("'-..-'|_.-;;-._|\n");
        bw.write("'-..-'|   ||   |\n");
        bw.write("'-..-'|_.-''-._|\n");
        bw.flush();
        bw.close();
        br.close();
    }

}