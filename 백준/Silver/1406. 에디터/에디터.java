import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static LinkedList<Character> words = new LinkedList<>();
    static int N;
    static ListIterator<Character> it;

    public static void main(String[] args) throws IOException {
        String init = br.readLine();
        for (Character ch : init.toCharArray()) {
            words.add(ch);
        }
        it = words.listIterator();

        // 마지막 순서로 이동
        while (it.hasNext()) {
            it.next();
        }

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String Command = st.nextToken();
            Character ch = null;
            if (st.hasMoreTokens()) {
                ch = st.nextToken().charAt(0);
            }
            process(Command, ch);
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : words) {
            sb.append(c);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void process(String command, Character ch) {
        if (command.equals("P")) {
            it.add(ch);
            return;
        }

        if (command.equals("L")) {
            if (it.hasPrevious()) {
                it.previous();
            }
            return;
        }

        if (command.equals("D")) {
            if (it.hasNext()) {
                it.next();
            }
            return;
        }

        // B이고 맨 앞이면 삭제 X
        if (!it.hasPrevious()) {
            return;
        }
        it.previous();
        it.remove();
    }
}