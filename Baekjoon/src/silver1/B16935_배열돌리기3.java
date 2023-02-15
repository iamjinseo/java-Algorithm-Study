package silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B16935_배열돌리기3 {
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

		st = new StringTokenizer(br.readLine()); // 회전 명령들
		for (int i = 0; i < R; i++) { // 회전 시작
			int command = Integer.parseInt(st.nextToken());
			switch (command) {
			case 1:
				rotate1(); // 상하반전
				break;
			case 2:
				rotate2(); // 좌우반전
				break;
			case 3:
				rotate3();
				break;
			case 4:
				rotate4();
				break;
			case 5:
				rotate5();
				break;
			case 6:
				rotate6();
				break;

			default:
				break;
			}
		}

		// 출력부분
		StringBuilder sb = new StringBuilder();
		for (int[] arr : map) {
			for (int i : arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	// 배열을 상하 반전
	static void rotate1() {
		// 서로 반전시켜야 하는 행은 대칭임
		for (int i = 0; i < N / 2; i++) {
			int[] temp = map[map.length - 1 - i]; // 6x6이면 3행, 4행, 5행

			map[map.length - 1 - i] = map[i]; // map[i]: 0행, 1행, 2행
			map[i] = temp;
		}
	}

	// 배열을 좌우 반전
	static void rotate2() {
		int[][] newMap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][M-1-j] = map[i][j];
			}
		}
		map = newMap;
	}

	// 배열을 90도로 우회전
	static void rotate3() {
		// 0열인거 0행, 1열인거 1행으로 보내야 함
		int[][] newMap = new int[M][N]; // 90도 회전하므로 행, 열 크기 달라짐

		// 기존 맵이 맨 왼쪽 열->맨 오른쪽 열, 그리고 맨 아래 행->맨 위 행의 진행방식이라면
		// 바뀌어야 하는 맵의 진행방식은 맨 위 행->맨 아래 행, 그리고 맨 왼쪽 열->맨 오른쪽 열 진행방식임
		for (int j = 0; j < M; j++) {
			for (int i = N - 1; i >= 0; i--) {
				newMap[j][N - 1 - i] = map[i][j]; // newMap에선 j가 행이 돼야 함
			}
		}
		int temp = M;
		M = N;
		N = temp;
		
		map = newMap;
	}

	// 배열 90도로 좌회전
	static void rotate4() {
		// 0행인거 0열, 1행인거 1열로 보내야 함
		int[][] newMap = new int[M][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// map의 열이 왼쪽->오른쪽의 진행방향을 가지고
				// newMap의 행이 아래->위의 진행방향을 가져야 함
				newMap[M - 1 - j][i] = map[i][j];
			}
		}
		int temp = M;
		M = N;
		N = temp;
		
		map = newMap;
	}

	static void rotate5() {
		int[][] newMap = new int[N][M];

		for (int i = 0; i < N/2; i++) { //왼->오
			for (int j = 0; j < M/2; j++) {
				newMap[i][j+M/2] = map[i][j];
			}
		}
		for (int i = 0; i < N/2; i++) { // 오위 -> 오아래
			for (int j = M/2; j < M; j++) {
				newMap[i+N/2][j] = map[i][j];
			}
		}
		for (int i = N/2; i < N; i++) { //오아래 -> 왼아래
			for (int j = M/2; j < M; j++) {
				newMap[i][j-M/2] = map[i][j];
			}
		}
		for (int i = N/2; i < N; i++) { //왼아래 -> 위
			for (int j = 0; j < M/2; j++) {
				newMap[i-N/2][j] = map[i][j];
			}
		}
		map = newMap;
	}

	static void rotate6() {
		int[][] newMap = new int[N][M];
		
		for (int i = 0; i < N/2; i++) { //왼위 -> 왼아래
			for (int j = 0; j < M/2; j++) {
				newMap[i+N/2][j] = map[i][j];
			}
		}
		for (int i = N/2; i < N; i++) { //왼아래 -> 오른아래
			for (int j = 0; j < M/2; j++) {
				newMap[i][j+M/2] = map[i][j];
			}
		}
		for (int i = N/2; i < N; i++) { // 오른아래 -> 오른위
			for (int j = M/2; j < M; j++) {
				newMap[i-N/2][j] = map[i][j];
			}
		}
		for (int i = 0; i < N/2; i++) { //오른쪽 -> 왼쪽
			for (int j = M/2; j < M; j++) {
				newMap[i][j-M/2] = map[i][j];
			}
		}
		map = newMap;
	}
}
