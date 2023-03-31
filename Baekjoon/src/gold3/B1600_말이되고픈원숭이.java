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
		map = new int[H][W];
		System.out.println("H: " + H + "W: " + W);
		if(H==1 && W==1) {
			System.out.println(0);
			return;
		}

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) { // 1을 -1로
					map[i][j] = -1;
				}
			}
		} // end input

		go(new Pair(0, 0));
		if (map[H - 1][W - 1] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(map[H - 1][W - 1]);
		}
	}

	static void go(Pair p) {
		Queue<Pair> q = new ArrayDeque<Pair>();
		q.add(p);

		while (!q.isEmpty()) {
			Pair polled = q.poll();
			System.out.println("polled: " + polled);
			int i = polled.i;
			int j = polled.j;
			if (i == H - 1 && j == W - 1) {
				System.out.println("도달");
				return;
			}

			// 나이트스텝으로 이동 가능한지 검사
			Pair knightPair = isKnight(i, j);

			// 나이트스텝으로 이동 가능
			if (knightPair != null) {
				k -= 1;
				int ni = knightPair.i;
				int nj = knightPair.j;
				map[ni][nj] = map[i][j] + 1;
				System.out.println("나이트스텝 다음위치는 " + ni + ", " + nj + "이고 값은 " + map[ni][nj]);
				
				q.offer(new Pair(ni, nj));
			}
			// 나이트스텝으로 이동 불가
			else {
				for (int n = 0; n < 4; n++) {
					int ni = i + di[n];
					int nj = j + dj[n];
					
					if (isValidScope(ni, nj)) {
//						if (map[ni][nj] > 0) {
//							map[ni][nj] = Math.min(map[i][j] + 1, map[ni][nj]);
//							System.out.println("스텝 다음위치는 " + ni + ", " + nj + "이고 값은 " + map[ni][nj]);
//							q.offer(new Pair(ni, nj));
//						} 
						if (map[ni][nj] == 0) {
							map[ni][nj] = map[i][j] + 1;
							System.out.println("스텝 다음위치는 " + ni + ", " + nj + "이고 값은 " + map[ni][nj]);
							q.offer(new Pair(ni, nj));
						}
					}
				}
			}
		}
	}

	static Pair isKnight(int i, int j) {
		if (k <= 0)
			return null;

		// 오른쪽으로가서 아래로가는거
		int ni = i + knightStep[0][0];
		int nj = j + knightStep[0][1];

		if (!isValidScope(ni, nj))
			return null;
		if (map[ni][nj] == 0) {
			System.out.println(i + ", " + j + "에서 오른쪽 아래로 나이트스텝 가능.");
			return new Pair(ni, nj);
		}
		// 아래로가서 오른쪽으로 가는거
		else {
			ni = i + knightStep[1][0];
			nj = j + knightStep[1][1];

			if (!isValidScope(ni, nj))
				return null;
			if (map[ni][nj] == 0) {
				System.out.println(i + ", " + j + "에서 아래로가서 오른쪽으로 나이트스텝 가능.");
				return new Pair(ni, nj);
			}
		}
		return null;
	}

	static boolean isValidScope(int ni, int nj) {
		if (ni < 0 || ni >= H || nj < 0 || nj >= W)
			return false;
		return true;
	}
}
