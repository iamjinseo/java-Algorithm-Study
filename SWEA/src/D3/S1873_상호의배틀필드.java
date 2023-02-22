package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1873_상호의배틀필드 {
	static class Train {
		public int i;
		public int j;
		public int[] dir = new int[2]; // 전차가 향할 곳

		public Train(int i, int j, int[] dir) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
	}

	static int H, W; // 맵 크기
	static int map[][];
	static String command;
	static Train train;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = null; //정답 출력용
		int T = Integer.parseInt(br.readLine()); //테스트케이스

		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			H = Integer.parseInt(br.readLine());
			W = Integer.parseInt(br.readLine());
			map = new int[H][W];

			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j) - '0';

					// 전차 위치 찾기
					if (map[i][j] == '^') {
						train = new Train(i, j, new int[] { -1, 0 }); // 전차 좌표 넣음
					} else if (map[i][j] == 'v') {
						train = new Train(i, j, new int[] { 1, 0 });
					} else if (map[i][j] == '>') {
						train = new Train(i, j, new int[] { -1, 0 });
					} else if (map[i][j] == '<') {
						train = new Train(i, j, new int[] { -1, 0 });
					}
				}
			} // map입력 끝
			int N = Integer.parseInt(br.readLine());
			command = br.readLine(); // 명령어 입력
			/////// end input

			// 명령어 수행 시작
			for (int c = 0; c < command.length(); c++) {
				char C = command.charAt(c); // 명령어 하나
				
				// 포탄쏘기
				if (C == 'S') {
					shoot(train.i, train.j, train.dir);
					continue;
				} // 포탄쏘면 바로 다음으로 넘어감

				if (C == 'U') {// 위로
					train.dir[0] = -1;
					train.dir[1] = 0;
					map[train.i][train.j] = '^';
				} else if (C == 'D') { // 아래로
					train.dir[0] = 1;
					train.dir[1] = 0;
					map[train.i][train.j] = 'v';
				} else if (C == 'L') { // 왼쪽으로
					train.dir[0] = 0;
					train.dir[1] = -1;
					map[train.i][train.j] = '<';
				} else if (C == 'R') { // 오른쪽으로
					train.dir[0] = 0;
					train.dir[1] = 1;
					map[train.i][train.j] = '>';
				}

				
				if(!innerMap()) continue; //다음스텝이 범위 벗어나면 안됨
					

			} // 모든 명령어 수행 끝
		} // 테스트케이스 끝
	}
	
	//다음 스텝이 유효한지 검사
	static boolean innerMap() {
		int ni = train.i + train.dir[0];
		int nj = train.j + train.dir[1];
		
		if(ni>=0 && ni<H && nj>=0 && nj<W) 
			return true;
		return false;
	}

	static void shoot(int i, int j, int[] dir) {

	}
}
