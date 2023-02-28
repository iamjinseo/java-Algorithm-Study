package gold4;
import java.io.*;
import java.util.*;

public class B1987_알파벳 {
	static int[] di = { 0, 0, 1, -1 };
	static int[] dj = { 1, -1, 0, 0 };

	static int R, C;
	static int[][] map;
	static int res = Integer.MIN_VALUE; // 결과로 나올 최대 칸 수
	static boolean[] visit = new boolean[26]; // 알파벳을 방문했는지 나타낼것

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) - 'A';
			}
		} // end input
		dfs(0, 0, 0);
		System.out.println(res);
	}

	static void dfs(int i, int j, int depth) {
		// 기저조건
		if (visit[map[i][j]]) { // 방문한 적 있는 알파벳으로 넘어갔다는 건 망했다는 뜻 아닐까?
			res = Math.max(res, depth);
			return;
		}
		// 유도파트
		else {
			visit[map[i][j]] = true; // 방문
			for (int n = 0; n < 4; n++) {
				int ni = i + di[n];
				int nj = j + dj[n];

				if (ni < 0 || ni >= R || nj < 0 || nj >= C)
					continue;

				dfs(ni, nj, depth + 1);
			}
			visit[map[i][j]] = false; // 다른 경로 탐색을 위해 미방문 처리
		}
	}
}
