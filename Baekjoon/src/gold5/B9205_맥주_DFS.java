package gold5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B9205_맥주_DFS {
	static Point[] points;
	static int N;
	static boolean result;
	static boolean[] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int T = sc.nextInt();
		while (T-- > 0) {
			int N = sc.nextInt();

			points = new Point[N + 2]; // 집, 도착지, N개의 편의점 전부 담음

			for (int i = 0; i <= N + 1; i++) { // 0번은 집, 1~N까지 편의점, N+1이 페스티벌
				points[i] = new Point(sc.nextInt(), sc.nextInt());
			}

			visit = new boolean[N + 2];
			result = false;

			dfs(0);
//			bfs2();

			sb.append(result ? "happy\n" : "sad\n");
		}
		System.out.println(sb);
	}

	static void bfs2() {
		Queue<Integer> q = new LinkedList<>();
		q.add(0); // 집 좌표 객체 번호
		visit[0] = true;

		while (!q.isEmpty()) {
			int now = q.poll();

			if (now == N + 1) {
				result = true;
				return;
			}

			for (int next = 1; next <= N + 1; next++) {
				int dist = Math.abs(points[next].x - points[now].x) + Math.abs(points[next].y - points[now].y);
				if (dist <= 1000 && !visit[next] && !result) {
					q.add(next);
					visit[next] = true;
				}
			}
		}
	}

	// 현재 내가 위치하고 있는 정점번호(0부터 시작)
	static void dfs(int now) {
		visit[now] = true;

		// 페스티벌 장소에 왔을 때
		if (now == N + 1) {
			result = true;
			return;
		}

		for (int next = 1; next <= N + 1; next++) {
			int dist = Math.abs(points[next].x - points[now].x) + Math.abs(points[next].y - points[now].y);
			if (dist <= 1000 && !visit[next] && !result) 
				dfs(next);
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
