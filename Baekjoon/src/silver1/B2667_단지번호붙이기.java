package silver1;

import java.io.*;
import java.util.*;

public class B2667_단지번호붙이기 {
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int areas;
	static int homes;
	static int cnt;
	static ArrayList<Integer> res = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		} // 입력 끝

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visit[i][j]) {
					bfs(i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		Collections.sort(res);
		for (Integer i : res) {
			System.out.println(i);
		}
	}

	static void bfs(int i, int j) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(i, j));
		map[i][j] = 0;
		int size = 1; //현재 땅의 면적

		while (!q.isEmpty()) {
//			System.out.println(q);
			Pair p = q.poll();
			for (int n = 0; n < 4; n++) {
				int ni = p.i + di[n];
				int nj = p.j + dj[n];

				//불가능한 경우 continue
				if (ni < 0 || ni >= N || nj < 0 || nj >= N)
					continue;
				if(map[ni][nj]==0 || visit[ni][nj])
					continue;
				
				q.offer(new Pair(ni, nj));
				visit[ni][nj] = true;
				size++; //가능한 경우에 size+1
			}
		} // bfs끝
		res.add(size); //size넣기
	}

	static class Pair {
		int i, j;

		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
}
