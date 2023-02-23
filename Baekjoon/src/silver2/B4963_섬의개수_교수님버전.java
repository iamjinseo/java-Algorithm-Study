package silver2;

import java.util.*;

public class B4963_섬의개수_교수님버전 {
	static int w, h;
	static int[][] map;
	static boolean[][] visit;
	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dj = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			w = sc.nextInt();
			h = sc.nextInt();
			map = new int[h][w];
			visit = new boolean[h][w];

			if (w == 0 && h == 0)
				break; // 입력이 무제한 들어오다 0 0들어오면 끝남

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
				}
			} // end input

			int ans = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 && !visit[i][j]) { // 땅 있을 때 bfs
						bfs(i, j);
						ans++; // 인접한 땅 모두 찾고 개수 +1
					}
				}
			}
			System.out.println(ans);
		}
	}

	static void bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(i, j));
		visit[i][j] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int d = 0; d < 8; d++) {
				int ni = now.i + di[d];
				int nj = now.j + dj[d];
				if (ni >= 0 && ni < h && nj >= 0 && nj < w
					&& map[ni][nj]==1 && !visit[ni][nj]) {
					q.add(new Point(ni, nj));
					visit[ni][nj] = true;
				}
			}
		}
	}

	static class Point {
		int i, j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
}
