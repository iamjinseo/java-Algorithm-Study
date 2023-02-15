package silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B16935_배열돌리기3 {
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
				System.out.println("=======rotate 5 시작=======");
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
		// 서로 반전시켜야 하는 열은 대칭임
		int[] col = new int[M]; // 예를들어 6x6이면 0열, 1열, 2열
		int[] temp = new int[M]; // 3열, 4열, 5열
		for (int j = 0; j < M / 2; j++) {
			for (int i = 0; i < N; i++) {
				col[i] = map[i][j];
				temp[i] = map[i][M - 1 - j];
			} // 서로 대칭되는 열 정보 저장
			for (int i = 0; i < N; i++) {
				map[i][M - 1 - j] = col[i];
				map[i][j] = temp[i];
			} // 저장된 정보 기반으로 값 변경
		}
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
		map = newMap;
	}


//	static void rotate5() {
//		// 1그룹: 0,0 | 2그룹: 0, M/2 | 4그룹: N/2, 0 | 3그룹: N/2, M/2
//		int[][] d = { { 0, 0 }, { 0, M / 2 }, { N / 2, 0 }, { N / 2, M / 2 } };
//		// save=초기지점 넣음
//		// next=다음지점 넣음 그리고 다음지점위치에 save값 넣음
//		// save = next
//		int[][] save = new int[N / 2][M / 2];
//		int[][] next = new int[N / 2][M / 2];
//		
//		// save 부분배열 넣기
//		for (int i = 0; i < N/2; i++) {
//			for (int j = 0; j < M/2; j++) {
//				save[i][j] = map[i][j];
//			}
//		}
//		
//		for (int n = 1; n < d.length - 1; n++) {
//			//디버깅용
//			System.out.print("지금 시작 좌표는: ");
//			System.out.println(d[n][0]+", "+d[n][1]);
//			System.out.println("다음 좌표는: "+d[n+1][0]+", "+d[n+1][1]);
//			///////////////////////////
//
//			//디버깅용 코드
//			StringBuilder sb= new StringBuilder();
//			for (int[] a : save) {
//				for (int b : a) {
//					sb.append(b+" ");
//				}
//				sb.append("\n");
//			}
//			System.out.println("지금 save는");
//			System.out.println(sb);
//			////////////////////////////
//			
//			// next
//			int ni = d[n][0];
//			int nj = d[n][1];
//			
//			for (int i = ni; i < ni+N/2; i++) {
//				for (int j = nj; j < nj+M/2; j++) {
//					next[i-ni][j-nj] = map[i][j];
//					map[i][j] = save[i-ni][j-nj];
//				}
//				save = next;
//			}
//		}
//	}
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
		int newMap = new int[N][M];
	}
}
