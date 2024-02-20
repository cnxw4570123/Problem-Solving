import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[] words;
    static Set<Character> vowel = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    static int L, C;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        words = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            words[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(words);
        DFS(0, 0, 0, "");
        bw.write(ans.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void DFS(int idx, int count, int vCount, String s) {
        if (count == L && vCount > 0 && count - vCount > 1) {
            ans.append(s).append("\n");
            return;
        }
        for (int i = idx; i < C; i++) {
            if (vowel.contains(words[i])) {
                DFS(i + 1, count + 1, vCount + 1, s + words[i]);
                continue;
            }
            DFS(i + 1, count + 1, vCount, s + words[i]);
        }

    }
}