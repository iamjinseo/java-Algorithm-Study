package silver2;

import java.io.*;
import java.util.*;

public class B1012_유기농배추_BFS {
	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };
	static int M;
	static int N;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수

		for (int t = 1; t <= T; t++) {
			int res = 0; // tc별 결과

			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			int[][] lettuce = new int[K][2];

			for (int k = 0; k < K; k++) { // 배추 입력
				st = new StringTokenizer(br.readLine());
				int j = Integer.parseInt(st.nextToken());
				int i = Integer.parseInt(st.nextToken());
				lettuce[k][0] = i;
				lettuce[k][1] = j; // 배추좌표입력
				map[i][j] = 1; // 배추가 있는 곳에1
			}

			// 배추 위치에서 탐색
			for (int[] l : lettuce) {
				int i = l[0], j = l[1]; 
				if (map[i][j] == 1) { // 배추가 있을 때만 탐색시작
					BFS(i, j);
					res++;
				}
			}
			System.out.println(res);
		} // 테스트케이스 끝
	}

	static void BFS(int i, int j) {
		Queue<int[]> q = new LinkedList<>(); // BFS용 큐
		q.add(new int[]{i, j});

		while (!q.isEmpty()) {
			int[] polled = q.poll();
			int nowi = polled[0];
			int nowj = polled[1];
			map[nowi][nowj] = 0; //방문
			
			for (int n = 0; n < 4; n++) { // 사방탐색 시작
				int ni = nowi + di[n];
				int nj = nowj + dj[n];

				if (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] == 1) {
					q.add(new int[] {ni, nj});
				}
			}
			// 현재 위치에서 사방탐색이 전부 끝났음
		}
		return;
	}
}
