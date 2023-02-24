package silver1;

import java.io.*;
import java.util.*;

public class B2667_단지번호붙이기_DFS {
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static int N;
	static int[][] map;
	static boolean[][] visit;
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
					res.add(dfs(i, j, 1));
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

	static int dfs(int i, int j, int size) {
		visit[i][j] = true;
		
		for (int n = 0; n < 4; n++) {
			int ni = i + di[n];
			int nj = j + dj[n];
			
			if(ni<0 || ni>=N || nj<0 || nj>=N || map[ni][nj]!=1 || visit[ni][nj])
				continue;
			
			size = dfs(ni, nj, size+1);
		}
		return size;
	}
}
