package silver1;

import java.io.*;
import java.util.StringTokenizer;

/*
배열 가로세로중에 더 작은것을 골라 2로 나누면 회전해야하는 그룹 개수가 나옴
그러면 그 그룹마다 회전을 시작해야하는 첫번째 지점은 0,0 1,1 2,2 ... 가 될 것임
회전을 시작하면 각 사각형에 대해 di, dj를 이용해 아래->오른쪽->위->왼쪽으로 돌 것임
회전하면서 값은 어떻게 넣냐면 현재 지점의 값을 save로 둠
그리고 다음 스텝의 값을 따로 저장함(그다음 스텝에 값을 또 넣어야하니까)
저장 후에 save값을 다음 스텝으로 넘겨버림
그리고 다음 스텝의 위치로 이동함
 * */
public class B16926_배열돌리기1 {
	static int[] di = { 1, 0, -1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int N;
	static int M;
	static int R;

	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < R; i++) {
			rotate();
		}

		StringBuilder sb = new StringBuilder();
		for (int[] arr : map) {
			for (int i : arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void rotate() {
		int square = Math.min(N, M) / 2;
		int i, j, save, cur, index;
		for (int s = 0; s < square; s++) {
			i = j = s; // rotate시작 위치
			save = map[i][j]; // 각 사각형의 시작 지점 저장
			index = 0;

			while (index < 4) { // 4방향으로 돌 것
				int ni = i + di[index]; // 다음 스텝
				int nj = j + dj[index];

				if (ni < s || ni >= N - s || nj < s || nj >= M - s) { // 범위검사  
					index++;
					continue;
				} 

				cur = map[ni][nj]; // 이동한 지점의 값 저장
				map[ni][nj] = save; // 다음 스텝에 저장된 현재 값 넣음  
				save = cur; //이동한 지점의 값을 저장된 값으로 넣음
				i = ni; //이동
				j = nj;
			}
		}
	}
}
