import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String advertise = "advertise", dontMatter = "does not matter", doNotAdv = "do not advertise";
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (r > e - c) {
                System.out.println(doNotAdv);
            } else if (r == e - c) {
                System.out.println(dontMatter);
            } else{
                System.out.println(advertise);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}