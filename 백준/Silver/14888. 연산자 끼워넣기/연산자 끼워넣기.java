import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiFunction;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static int[] numbers, operatorCnt = new int[4];
	static Map<Integer, BiFunction<Integer, Integer, Integer>> operators = Map.of(
		0, Integer::sum,
		1, (a, b) -> a - b,
		2, (a, b) -> a * b,
		3, (a, b) -> a / b
	);
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operatorCnt[i] = Integer.parseInt(st.nextToken());
		}
		DFS(numbers[0], 1);
		bw.write(max + "\n" + min);
		bw.flush();
		bw.close();
		br.close();
	}

	public static void DFS(int res, int idx) {
		if (idx == N) {
			max = Math.max(res, max);
			min = Math.min(res, min);
			return;
		}
		for (int j = 0; j < 4; j++) {
			if (operatorCnt[j] <= 0) {
				continue;
			}
			operatorCnt[j]--;
			DFS(operators.get(j).apply(res, numbers[idx]), idx + 1);
			operatorCnt[j]++;
		}
	}
}
