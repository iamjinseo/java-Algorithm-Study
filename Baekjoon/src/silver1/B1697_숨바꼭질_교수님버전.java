package silver1;

import java.util.*;

public class B1697_숨바꼭질_교수님버전 {
	static int N, K; // 출발, 도착
	static int cnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		bfs();
		System.out.println(cnt);
	}

	static int bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(N)); // 출발점은 연산횟수 없이 그냥 만들어짐
		boolean[] visit = new boolean[100001];
		visit[N] = true;

		while (!q.isEmpty()) {
			int size = q.size(); // 연산 횟수 세기 위함

			for (int s = 0; s < size; s++) { // 큐 사이즈만큼만 for문 실행함
				Point now = q.poll();

				if (now.num == K) {
					return cnt; // 수행된 연산수
				}
				if (now.num + 1 <= 100000 && !visit[now.num + 1]) {
					q.add(new Point(now.num + 1));
					visit[now.num + 1] = true;
				}
				if (now.num - 1 >= 0 && !visit[now.num - 1]) {
					q.add(new Point(now.num - 1));
					visit[now.num - 1] = true;
				}
				if (now.num * 2 <= 100000 && !visit[now.num * 2]) {
					q.add(new Point(now.num * 2));
					visit[now.num * 2] = true;
				}
			} // for문 끝
			cnt++; // 출발지로
		}
		return -1;
	}

	static class Point {
		int num, cnt;

		public Point(int num) {
			super();
			this.num = num;
		}
	}
}
