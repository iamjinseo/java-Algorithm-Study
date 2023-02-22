package D5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S1247_최적경로 {
	static int[] office = new int[2]; // 회사 좌표
	static int[] home = new int[2]; // 집
	static int[][] customers; // 고객 좌표
	static int N; // 고객 수
	static int[][] result; // 고객 좌표 순열 결과
	static boolean[] used; // 순열에 쓰일 방문검사용 배열
	static int res; // 최단거리

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine()); // 고객 수 입력
			customers = new int[N][2]; // 고객좌표 선언
			result = new int[N][2];
			used = new boolean[N]; // 순열에 쓰일 배열들

			StringTokenizer st = new StringTokenizer(br.readLine()); // 회사, 집, 고객입력
			office[0] = Integer.parseInt(st.nextToken());
			office[1] = Integer.parseInt(st.nextToken());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			} // 고객좌표 입력 끝
			// end input
			
			//입력 디버깅
//			System.out.println("office: "+Arrays.toString(office));
//			System.out.println("home: "+Arrays.toString(home));
//			for (int i = 0; i<customers.length; i++) {
//				System.out.println("customer: "+Arrays.toString(customers[i]));
//			}

			res = Integer.MAX_VALUE;
			perm(0); // 고객 좌표 순열 시작

			System.out.printf("#%d %d\n", t, res);
		}
	}

	// 고객 좌표를 순열으로 돌릴 것임
	static void perm(int idx) {
//		System.out.println("start idx: "+idx);
		
		if (idx == N) { // 순열 생성 끝
//			System.out.println("순열 끝");
			int distance = getDistance(); // 모든 거리 좌표 구하기
//			System.out.println("res: "+res+", distance: "+distance);
			res = Math.min(res, distance);
//			System.out.println("final res: "+res);
		} // 기저조건 끝

		for (int i = 0; i < N; i++) {
			if (used[i])
				continue;

			result[idx][0] = customers[i][0];
			result[idx][1] = customers[i][1];
			used[i] = true;
			perm(idx + 1);
			used[i] = false;
		}
	}

	// 거리계산
	// (x1, y1)와 (x2, y2) 사이의 거리는 |x1-x2| + |y1-y2|으로 계산
	static int getDistance() {
//		for (int[] is : customers) {
//			System.out.println(Arrays.toString(is));
//		}
		
		int dis=0;
		// office to 순열1
		dis += Math.abs(office[0]-result[0][0])+Math.abs(office[1]-result[0][1]);
		// 순열1~순열 끝
		int i;
		for (i = 0; i < result.length-1; i++) {
			dis += Math.abs(result[i][0]-result[i+1][0])+Math.abs(result[i][1]-result[i+1][1]);
		}
		// 순열 끝~home
		dis +=Math.abs(home[0]-result[i][0])+Math.abs(home[1]-result[i][1]);
		return dis;
	}
}
