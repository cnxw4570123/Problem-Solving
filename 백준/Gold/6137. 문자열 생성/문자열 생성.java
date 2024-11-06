import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static char[] S;
	static List<Character> ans = new ArrayList<>();
	static int N;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		S = new char[N];
		for (int i = 0; i < N; i++) {
			S[i] = (br.readLine().charAt(0));
		}

		int start = 0, last = N - 1;
		while (start <= last) {
			if (S[start] < S[last]) {
				ans.add(S[start]);
				start++;
			} else if (S[start] > S[last]) {
				ans.add(S[last]);
				last--;
			} else {
				int left = start, right = last;
				while (left < right && S[left] == S[right]) {
					left++;
					right--;
				}
				if (S[left] <= S[right]) {
					ans.add(S[start]);
					start++;
				} else {
					ans.add(S[last]);
					last--;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			if (i != 0 && i % 80 == 0) {
				bw.write("\n");
			}
			bw.write(ans.get(i));
		}
		bw.flush();
		bw.close();
		br.close();
	}

}

