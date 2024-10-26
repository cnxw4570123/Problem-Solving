import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static String S, T;

	public static void main(String[] args) throws IOException {
		S = br.readLine();
		T = br.readLine();
		DFS(T);
		bw.write("0");
		bw.flush();
		bw.close();
		br.close();
	}

	static void DFS(String subject) throws IOException{
		if (subject.equals(S)) {
			bw.write("1");
			bw.flush();
			System.exit(0);
		}
		if (subject.length() == 1) {
			return;
		}
		int lastIdx = subject.length() - 1;
		//
		if(subject.charAt(lastIdx) == 'A'){
			DFS(subject.substring(0, lastIdx));
		} else if (subject.charAt(lastIdx) == 'B') {
			StringBuilder sb = new StringBuilder(subject);
			DFS(sb.deleteCharAt(lastIdx).reverse().toString());
		}

	}
}

