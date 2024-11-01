import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static long ans = 0;
	static int[] numbers;
	static Set<Integer> included = new HashSet<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0, end = 0;

		while (end < N) {
			if (!included.add(numbers[end])) {
				included.remove(numbers[start]);
				start++;
				continue;
			}
			end++;
			ans += included.size();
		}
		bw.write(ans + "");
		bw.flush();
		bw.close();
		br.close();
	}

}

