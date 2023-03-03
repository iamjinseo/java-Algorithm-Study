package no_rank;

import java.io.*;
import java.util.*;

/*
 * 1. 벽돌깨는 위치 찾기 -> 중복 순열
 * 2. 벽돌 깨기(맨 첫번째 행 찾기)
 * 	2-1. 벽돌 하나도 없으면 다음 순열(가지치기)
 * 3. 벽돌 찾았으면 맵 복사. 현재 단계의 맵 저장해야하기 때문
 * 4. 벽돌 깨기
 * 5. 벽돌 아래로 내리기
 * */
public class S5656_벽돌깨기_교수님버전 {
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static int N, W, H; // 벽돌깨기 횟수, 맵 가로, 맵 세로
	static int res;
	static int[][] map;

	static class Point {
		int i, j, size; // 벽돌 i, j좌표와 벽돌의 값

		public Point(int i, int j, int size) {
			super();
			this.i = i;
			this.j = j;
			this.size = size;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		// start testcase
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append(' ');
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end input

			// 벽돌깨기 시작
			res = Integer.MAX_VALUE;
			perm(0, map); // 중복순열로 벽돌 깨기
			sb.append(res).append('\n');
		} // end tescase
		System.out.println(sb);
	}

	// 중복순열로 벽돌 깰 위치 찾기
	static void perm(int cnt, int[][] map) {
		// 기저조건: N 번 뿌숨
		if (cnt == N) {
			// 남은 벽돌 개수 세기
			int res2 = getBricks(map);
			res = Math.min(res, res2);
			return;
		}
		// 유도파트 시작
		int newMap[][] = new int[H][W];
		// 중복순열이므로 매 재귀마다 모든 열에 대해 검사할 것임
		for (int w = 0; w < W; w++) {
			int loc = 0; // 벽돌있는 곳
			// 벽돌 만날 때까지 계속 아래로 내리기
			while (loc < H && map[loc][w] == 0)
				loc++;

			// 만약 벽돌이 아무것도 없으면 다음재귀: 가지치기
			if (loc == H) { // 벽돌이 없어서 H까지 와버림
				perm(cnt + 1, map);
			}
			// 벽돌 있음
			else {
				// 맵 복사: 현재 단계의 상태 저장
				copy(newMap, map);

				// 벽돌 깨버리기
				breakBrick(newMap, loc, w);

				// 벽돌 아래로 내리기
				downBrick(newMap);

				// 다음 재귀
				perm(cnt + 1, newMap);
			}
		}
		// 유도파트 끝
	}

	// 남은 벽돌 개수 구하기
	private static int getBricks(int[][] map) {
		int cnt=0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]>0) cnt++;
			}
		}
		return cnt;
	}

	// 벽돌 깨기: BFS로 해결
	// 연쇄깨기: 일단 2이상인 벽돌에 대해 네방향 탐색한다음, 벽돌의 size만큼 전진하며 파괴
	static void breakBrick(int[][] newMap, int nowi, int nowj) {
		Queue<Point> q = new ArrayDeque<Point>(); // BFS용 큐

		// 첫번째 벽돌이 2 이상이어야만 연쇄파괴할거임
		if (newMap[nowi][nowj] > 1)
			q.offer(new Point(nowi, nowj, newMap[nowi][nowj]));

		newMap[nowi][nowj] = 0; // 벽돌 파괴

		// BFS 시작
		while (!q.isEmpty()) {
			Point polled = q.poll();

			// 사방탐색
			for (int n = 0; n < 4; n++) {
				// 벽돌의 원래 위치
				int ni = polled.i;
				int nj = polled.j;

				// 벽돌 숫자만큼 한 방향으로 전진하며 파괴할 것
				for (int k = 1; k < polled.size; k++) {
					ni += di[n];
					nj += dj[n];
					// 범위검사, 벽돌 있는지 검사
					if (ni < 0 || ni >= H || nj < 0 || nj >= W || newMap[ni][nj]==0)
						continue;
					
					// 연쇄파괴 할 수 있는지 검사
					if(newMap[ni][nj]>1)
						q.offer(new Point(ni, nj, newMap[ni][nj])); //여기도  연쇄파괴 해야댐
						
					newMap[ni][nj]=0; // 벽돌 부수기
				}
			}
		}
	}

	// 벽돌 아래로 내리기
	// 큐를 이용해서 밑에서부터 벽돌만 담은다음, 담을 때 0으로 값 바꿈
	// 그리고 또 밑에서부터 큐에 있는 값 넣기
	static void downBrick(int[][] newMap) {

		// 모든 열에 대해
		for (int j = 0; j < W; j++) {
			Queue<Integer> q = new ArrayDeque<Integer>();
			// 행 밑에서부터 검사
			for (int i = H-1; i >= 0; i--) {
				int value = newMap[i][j];
				if(value>0) q.offer(value);
				newMap[i][j]=0;
			}
			
			// 벽돌값 넣기
			int i = H-1;
			while(!q.isEmpty()) { // 밑에서부터 큐에 있는 값 없어질 때까지만 넣기
				newMap[i][j] = q.poll(); 
				i--;
			}
		}
	}

	// 배열 복사
	static void copy(int[][] newMap, int[][] map) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
}
