package gold4;

import java.io.*;
import java.util.*;

/* 물이 흐를 위치로 갈 수 없으므로 BFS를 실행할 때, 물흐르는 BFS->비버의 BFS 순으로 해야함
 * 또한 물 흐르는 BFS를 비버가 이동할 때마다 하는 게 아니라, 비버의 이동 타임에 맞춰 흘러야함
 * 비버 큐에서 poll할 때마다 물 흐르기 BFS를 실행하면 안됨 */
public class B3055_탈출 {
	// 비버와 물
	static class Pair {
		int i, j, time;

		public Pair(int i, int j, int time) {
			this.i = i;
			this.j = j;
			this.time = time;
		}
	}

	static boolean[][] visit;
	static char[][] map;
	static int R, C; // 행, 열
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static Pair bb; // 비버
	static Pair cave; // 동굴
	static Queue<Pair> waters = new LinkedList<>(); // 물 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == 'S') {
					bb = new Pair(i, j, 0);
				} else if (map[i][j] == 'D') {
					cave = new Pair(i, j, 0);
				} else if (map[i][j] == '*') {
					waters.offer(new Pair(i, j, 0));
				}
			}
		} // end input

		bfs(bb);
		System.out.println("KAKTUS"); // exit하지 못했다면 이거 출력
	}

	// 비버의 이동 시작
	static void bfs(Pair bb) {
		Queue<Pair> q = new LinkedList<>(); // 비버
		q.offer(bb);
		visit[bb.i][bb.j] = true;
		int preTime = -1;

		while (!q.isEmpty()) {

			Pair polled = q.poll();
			int i = polled.i;
			int j = polled.j;
			int time = polled.time;
			if (time != preTime) {
				flow(); // 비버가 이동하기 전, 같은 타임에 한 번만 물이 흘러야 한다.
				preTime = time;
			}
			for (int n = 0; n < 4; n++) {
				int ni = i + di[n];
				int nj = j + dj[n];

				if (!isValid(ni, nj) || visit[ni][nj])
					continue;
				if (ni == cave.i && nj == cave.j) { // 동굴 찾음
					System.out.println(time + 1);
					System.exit(0);
				}
				if (map[ni][nj] != '.')
					continue;

				q.offer(new Pair(ni, nj, time + 1));
				visit[ni][nj] = true;
			}
		}
	}

	// 범위 밖이거나 방문했거나 돌이면 안됨
	private static boolean isValid(int ni, int nj) {
		if (ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] == 'X')
			return false;
		return true;
	}

	// 물이 타임에 따라 한 번 흐른다.
	static void flow() {
		int flowCnt = waters.size(); // 흘러야 되는 물 개수

		// !watesr.isEmpty()로 하면 타임 상관없이 계속 흐름
		while (flowCnt-- > 0) { // waters에 있는 물 개수만큼 흘려야된다.
			Pair polled = waters.poll();
			int i = polled.i;
			int j = polled.j;

			for (int n = 0; n < 4; n++) {
				int ni = i + di[n];
				int nj = j + dj[n];

				if (!isValid(ni, nj))
					continue;
				if (map[ni][nj] != '.')
					continue;

				map[ni][nj] = '*';
				waters.offer(new Pair(ni, nj, 0));
			}
		}
	}
}