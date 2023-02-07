package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1012_유기농배추 {

	/* 맵을 순회하면서 1을 하나 발견하면 인접한 1을 모두 탐색하여 0으로 만든 후 결과에 1더함
	 * int map = 100 100
	 *  while true
	 *   0,0에서 탐색 시작
	 *   1을 감지함.
	 *     지렁이 += 1 
	 *     상하좌우로 움직이는 반복문 시작
	 * 	     1에서 상하좌우로 움직임 만약 1나옴
	 *  	 => 그자리로 옮기기
	 *       => 0으로 만들기
	 *       1이 한번도 안나옴? => 0,1탐색 ㄱㄱ...
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		int res = 0;
		int[] di = { 0, 0, 1, -1 };
		int[] dj = { 1, -1, 0, 0 };

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int M = Integer.parseInt(br.readLine());
			int N = Integer.parseInt(br.readLine());
			int K = Integer.parseInt(br.readLine());

			int[][] map = new int[N][M];

			for (int k = 0; k < K; k++) { // 배추 입력
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				map[i][j] = 1;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) { //1감지
						res++;
						for (int n = 0; n < 4; n++) {
							int ni = i + di[n];
							int nj = j + dj[n];
							if(ni>=0 && ni<N && nj>=0 && nj<M && map[ni][nj]==1) {
								i = ni;
								j = nj; 
								map[i][j] = 0;
							}
						} //사방탐색 끝
						
					} //1 감지 끝
				} //맵 탐색 끝
				System.out.println(res);
			}
				
		} //테스트케이스 끝
		
	}

}
