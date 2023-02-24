package silver2;

import java.util.*;

public class B4963_섬의개수_DFS {
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
					if (map[i][j] == 1 && !visit[i][j]) { // 땅 있을 때 dfs
						dfs(i, j);
						ans++; // 인접한 땅 모두 찾고 개수 +1
					}
				}
			}
			System.out.println(ans);
		}
	}

	static void dfs(int i, int j) {
		visit[i][j] = true;
		
		for (int n = 0; n < 8; n++) {
			int ni = i + di[n];
			int nj = j + dj[n];

			if(ni<0 || ni>=h || nj<0 || nj>=w || visit[ni][nj] || map[ni][nj]!=1) 
				continue;
			
			dfs(ni, nj);
		}
	}
}
