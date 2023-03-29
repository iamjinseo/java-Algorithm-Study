package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1600_말이되고픈원숭이 {
	static int[][] knightStep = { { 1, 2 }, { 2, 1 } };
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static int W, H, k;
	static int[][] map;

	static class Pair {
		int i, j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) {
					map[i][j] = -1;
				}
			}
		} // end input

		go(new Pair(0, 0));
	}

	static void go(Pair p) {
		Queue<Pair> q = new ArrayDeque<Pair>();
		q.add(p);
		
		while (!q.isEmpty()) {
			Pair polled = q.poll();
			int i = polled.i;
			int j = polled.j;
			
			// 나이트스텝으로 이동 가능한지 검사
			Pair knightPair = isKnight(i, j);
			
			// 나이트스텝으로 이동 가능
			if (knightPair != null) {
				k -= 1;
				int ni = knightPair.i;
				int nj = knightPair.j;
				map[ni][nj] = map[i][j] + 1;
				if(ni==H-1 && nj==W-1) return;
				q.offer(new Pair(ni, nj));
			} 
			// 나이트스텝으로 이동 불가
			else {
				for (int n = 0; n < 4; n++) {
					int ni = i + di[n];
					int nj = j + dj[n];
					if(ni==H-1 && nj==W-1) return;
					if (isValidScope(ni, nj))
						map[ni][nj] = map[i][j] + 1;
				}
			}
		}
	}

	static Pair isKnight(int i, int j) {
		if(k<=0) return null;
		
		// 오른쪽으로가서 아래로가는거
		int ni = i + knightStep[0][0];
		int nj = j + knightStep[0][1];

		if (!isValidScope(ni, nj))
			return null;
		if (map[ni][nj] == 0) {
			return new Pair(ni, nj);
		}
		// 아래로가서 오른쪽으로 가는거
		else {
			ni = i + knightStep[1][0];
			nj = j + knightStep[1][1];

			if (!isValidScope(ni, nj))
				return null;
			if (map[ni][nj] == 0)
				return new Pair(ni, nj);
		}
		return null;
	}

	static boolean isValidScope(int ni, int nj) {
		if (ni < 0 || ni >= H || nj < 0 || nj >= W)
			return false;
		return true;
	}
}
