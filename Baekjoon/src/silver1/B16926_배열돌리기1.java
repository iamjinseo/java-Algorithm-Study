package silver1;

import java.io.*;
import java.util.StringTokenizer;

/*
 * 1,1이 1,2의 값 받음
 * 1, 2가 1,3의 값 받음
 * 반시계 회전시키려면 시계방향으로 회전하며 이전 값에 내 값을 줘야 함
 * */
public class B16926_배열돌리기1 {
	int[] di = {1, 0, -1, 0};
	int[] dj = {0, 1, 0, -1};
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < R; i++) {
			rotate(1, 0);
		}
	}
	static void rotate(int i, int j) {
		for (int k= 0; j2 < map.length; j2++) {
			
		}
		
	}
}
