import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String org, target;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        org = br.readLine();
        target = br.readLine();
        find(target);
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void find(String target){
        if (target.length() < org.length()) {
            return;
        }

        if (target.equals(org)) {
            ans = 1;
            return;
        }
        if(target.charAt(0) == 'B'){
            StringBuilder sb = new StringBuilder(target).deleteCharAt(0).reverse();
            find(sb.toString());
        }
        if(target.charAt(target.length() - 1) == 'A'){
            StringBuilder sb = new StringBuilder(target);
            sb.deleteCharAt(sb.length() - 1);
            find(sb.toString());
        }
    }
}
