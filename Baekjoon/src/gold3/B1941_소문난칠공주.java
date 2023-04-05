package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1941_소문난칠공주 {
	static class Pair {
		int i, j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

	}

	static char[][] map = new char[5][5];
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static boolean[][] visit = new boolean[5][5];
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
			}
		} // end input

		combi();
		System.out.println(res);
	}
	
	

	// 범위 밖이거나 방문했거나 돌이면 안됨
	private static boolean isValid(int ni, int nj) {
		if (ni < 0 || ni >= 5 || nj < 0 || nj >= 5 || visit[ni][nj])
			return false;
		return true;
	}
}
