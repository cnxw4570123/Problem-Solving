import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, y, x, direction = 0;
	static int[] dy = {0, 1, 0, -1}, dx = {-1, 0, 1, 0};
	static int[][] A;
	static boolean[][] v;
	static SandDirection[] sandDirections = {
		new SandDirection(-2, 0, 2),
		new SandDirection(-1, -1, 10),
		new SandDirection(-1, 0, 7),
		new SandDirection(-1, 1, 1),
		new SandDirection(0, -2, 5),
		new SandDirection(1, -1, 10),
		new SandDirection(1, 0, 7),
		new SandDirection(1, 1, 1),
		new SandDirection(2, 0, 2)
	};
	static SandDirection leftSand = new SandDirection(0, -1);

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];
		v = new boolean[N][N];
		int ans = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				ans += A[i][j];
			}
		}

		y = N / 2;
		x = N / 2;
		proceed(N, 0);

		for (int[] a : A) {
			for (int j : a) {
				ans -= j;
			}
		}
		bw.write(ans + "");
		bw.flush();
		bw.close();
		br.close();
	}

	static class SandDirection {
		int y;
		int x;
		int percentage;

		public SandDirection(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public SandDirection(int y, int x, int percentage) {
			this.y = y;
			this.x = x;
			this.percentage = percentage;
		}

		public int[] adjustPos(int r, int c, int direction) {
			// 왼쪽
			if (direction == 0) {
				return new int[] {r + y, c + x};
			}
			// 아래
			if (direction == 1) {
				return new int[] {r - x, c + y};
			}
			// 오른쪽
			if (direction == 2) {
				return new int[] {r + y, c - x};
			}
			// 위
			return new int[] {r + x, c - y};
		}

		public void handleLeft(int r, int c, int direction, int left) {
			int[] pos = adjustPos(r, c, direction);
			if (isNotMovable(pos)) {
				return;
			}
			A[pos[0]][pos[1]] += left;
		}

		public int fly(int r, int c, int direction) {
			int amount = (A[r][c] * percentage) / 100;
			int[] pos = adjustPos(r, c, direction);
			if (!isNotMovable(pos)) {
				A[pos[0]][pos[1]] += amount;
			}
			return amount;
		}

		public boolean isNotMovable(int... position) {
			for (int pos : position) {
				if (pos >= N || pos < 0) {
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			return "{" +
				"y=" + y +
				", x=" + x +
				", " + percentage +
				"%}";
		}
	}

	static void proceed(int size, int start) {
		if (size == 1) {
			return;
		}
		proceed(size - 2, start + 1);

		int count = 0;

		while (count != 4 * (size - 1)) {
			for (int i = 0; i < 4; i++) {
				int nd = (direction + i) % 4;
				int ny = y + dy[nd], nx = x + dx[nd];
				if (ny >= start + size || nx >= start + size || ny < start || nx < start || v[ny][nx]) {
					continue;
				}
				count++;
				// 토네이도 이동
				y = ny;
				x = nx;
				v[y][x] = true;
				direction = nd;
				int left = A[y][x];

				// 토네이도가 이동한 자리의 모래 이동
				for (SandDirection sandDirection : sandDirections) {
					left -= sandDirection.fly(y, x, direction);
				}
				leftSand.handleLeft(y, x, direction, left);
				A[y][x] = 0;

				break;
			}
		}

	}

}

