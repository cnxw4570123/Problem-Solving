import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(LocalDate.now().toString());
        bw.close();
        br.close();
    }
}