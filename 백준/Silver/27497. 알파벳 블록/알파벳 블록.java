import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Deque<Word> dq = new ArrayDeque<>();

	static int N;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			int command = s.charAt(0) - '0';
			if (command == 3) {
				if (!dq.isEmpty()) {
					int first = dq.peekFirst().idx;
					int last = dq.peekLast().idx;

					if (first > last) {
						dq.pollFirst();
					}else{
						dq.pollLast();
					}
				}
				continue;
			}

			Word target = new Word(i, s.charAt(2));
			if(command == 1){
				dq.add(target);
				continue;
			}
			dq.addFirst(target);
		}

		StringBuilder sb = new StringBuilder();
		while (!dq.isEmpty()) {
			sb.append(dq.pollFirst().value);
		}
		if (sb.length() == 0) {
			sb.append("0");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static class Word{
		int idx;
		char value;

		public Word(int idx, char value) {
			this.idx = idx;
			this.value = value;
		}
	}
}

