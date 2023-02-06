package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1210_ladder {

	// 출발지점에서부터 시작점 찾기
	// 출발지점에서 위로 계속 올라가기
	// 좌, 우 통로 있으면 거기로 가기
	// 그러면서 계속 올라가기 !! 이동할 때는 무조건 visited
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[100][100];
		int dest_i = 0; // 목적지 i주소
		int dest_j = 0; // 목적지 j주소
		int[][] d = { // 이동필터
				{ 0, -1 }, // 좌
				{ 0, 1 }, // 우
				{ -1, 0 } // 상
		};

		for (int t = 1; t <= 10; t++) {
			String asdf = br.readLine(); // 굳이 숫자 하나 넣기
			// 배열에 사다리 값 넣기
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 2) { // 도착지 찾았을 때
						dest_i = i;
						dest_j = j;
					}
				}
			} ///////////// 사다리 값 넣기 끝 ////////////////////

			// 도착지점에서 점점 위로 올라가기
			int i = dest_i, j = dest_j;
			while (i != 0) { // 최상단에 도달할 때까지
//				System.out.println("i: "+ i+" j: "+j);
				for (int n = 0; n < 3; n++) { // 좌,우,상 으로 이동
					int ni = i + d[n][0];
					int nj = j + d[n][1];
					if (ni >= 0 && ni < 100 && nj >= 0 && nj < 100) {
						if (map[ni][nj] == 1) {// 범위내이며 1일때 이동
							i = ni;
							j = nj;
							map[ni][nj] = -1;
							break;
						}
					}
				}
			}
			//j가 0이므로 출발지에 도달함
			System.out.printf("#%d %d", t, j);

		} // 테스트케이스 끝
	}

}