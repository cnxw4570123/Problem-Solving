import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static String[] words;
    static Set<Character> shortcutLowerCase = new HashSet<>();
    static int[] shortcutIdx;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        shortcutIdx = new int[N];
        Arrays.fill(shortcutIdx, -1);
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            StringTokenizer st = new StringTokenizer(words[i]);
            int idx = 0;
            while (st.hasMoreTokens()) {
                String current = st.nextToken();
                Character ch = Character.toLowerCase(current.charAt(0));
                if (!shortcutLowerCase.contains(ch)) {
                    // 글자 지정
                    shortcutLowerCase.add(ch);
                    shortcutIdx[i] = idx;
                    break;
                }
                idx += current.length() + 1;
            }

            // 갱신됐으면 탐색 X
            if (shortcutIdx[i] != -1) {
                continue;
            }

            for (int j = 0; j < words[i].length(); j++) {
                char ch = Character.toLowerCase(words[i].charAt(j));
                if (ch == ' ' || shortcutLowerCase.contains(ch)) {
                    continue;
                }
                shortcutIdx[i] = j;
                shortcutLowerCase.add(ch);
                break;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if(shortcutIdx[i] == -1){
                ans.append(words[i]).append("\n");
                continue;
            }
            ans.append(words[i].substring(0, shortcutIdx[i]));
            ans.append("[").append(words[i].charAt(shortcutIdx[i])).append("]");
            ans.append(words[i].substring(shortcutIdx[i] + 1)).append("\n");
        }

        bw.write(ans.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
