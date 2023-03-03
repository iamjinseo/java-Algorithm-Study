package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7576_토마토 {
	static class Point {
		int i, j, day;

		public Point(int i, int j, int day) {
			super();
			this.i = i;
			this.j = j;
			this.day = day;
		}

	}

	static int[] di = { 0, 0, 1, -1 };
	static int[] dj = { 1, -1, 0, 0 };
	static int N, M, ans;
	static int[][] map;
	static Queue<Point> q = new ArrayDeque<Point>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) // 익은 토마토
					q.offer(new Point(i, j, 0)); // 퍼트리기 전에는 0일차
			}
		} // end input

		while (!q.isEmpty()) {
			Point tomato = q.poll();

			int i = tomato.i;
			int j = tomato.j;
			int day = tomato.day;

			for (int n = 0; n < 4; n++) {
				int ni = i + di[n];
				int nj = j + dj[n];
				int nday = day + 1;

				// 범위검사 밑 탐색할 수 없으면 다음 스텝
				if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] != 0)
					continue;

				q.offer(new Point(ni, nj, nday));
				map[ni][nj] = 1;
				ans = nday;
			}
		} // end bfs

		// 맵 전체 검사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					// 뭔가 안익은 게 있음
					System.out.println(-1);
					System.exit(0); // 종료
				}
			}
		}
		// 안익은 건 없는데 애초에 전부 익어있었음=>ans는 0
		if (ans == 0) {
			System.out.println();
		} else {
			System.out.println(ans); // 다 익었으면
		}
	}
}
