import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static Map<Integer, String> pokemonIdx = new HashMap<>();
    static Map<String, Integer> pokemonName = new HashMap<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseUnsignedInt(st.nextToken());
        M = Integer.parseUnsignedInt(st.nextToken());
        for (int i = 1; i < N + 1; i++) {
            String input = br.readLine();
            pokemonName.put(input, i);
            pokemonIdx.put(i, input);
        }

        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            try {
                int idx = Integer.parseInt(input);
                bw.write(pokemonIdx.get(idx) + "\n");

            } catch (NumberFormatException e) {
                bw.write(pokemonName.get(input) + "\n");
            }
        }

        bw.close();
        br.close();
    }
}
