package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1873_상호의배틀필드_교수님버전 {
	static int H, W;
	static char[][] map;
	static char[] orders;
	static int tanki, tankj, tankd; // 전차는 맵에 두지 않고 공중부양

	// 0: up, 1:down, 2:left, 3:right
	static char[] arrow = { '^', 'v', '<', '>' };
	static char[] orderDir = { 'U', 'D', 'L', 'R' };
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					for(int d=0; d<4; d++) { // 0: up, 1:down, 2:left, 3:right
						if(map[i][j] == arrow[d]) { //탱크 초기화위치 찾음(화살표 4개중에 하나)
							tanki = i;
							tankj = j;
							tankd = d;
							map[i][j] = '.';
						}
					}
				}
			}
			int orderCnt = Integer.parseInt(br.readLine()); //명령어 개수
			orders = br.readLine().toCharArray(); //명령어들
			//      end input
			
			// 		명령 시작
			for (char op : orders) { //명령어 하나씩. U D L R S
				if(op == 'S') { //포탄쏘기거나
					int nowi = tanki; //포탄 시작 좌표들
					int nowj = tankj;
					
					while(true) {
						int nexti = nowi + di[tankd]; //탱크가 바라보는 방향으로 ㄱㄱ
						int nextj = nowj + dj[tankd];
						
						// 범위 벗어나거나 강철벽이면 그냥 멈춤
						if(nexti>=H || nexti<0 || nextj>=W || nextj < 0 || map[nexti][nextj]=='#')
							break; 
						
						// 벽돌 벽일땐 부수고 멈춤
						if(map[nexti][nextj]=='*') {
							map[nexti][nextj] = '.';
							break;
						}
						
						// 안멈췄으면 계속 직진
						nowi = nexti;
						nowj = nextj;
					}
				}else { //이동이거나
					for (int d = 0; d < 4; d++) { // 'U'를 0으로, 'D'를 1로 각각 문자를 숫자방향으로 바꾸기
						if(op == orderDir[d]) {
							tankd = d; //탱크가 명령한 방향을 쳐다보게 함. 
							int nexti = tanki + di[tankd]; //탱크가 바라보는 방향으로 ㄱㄱ
							int nextj = tankj + dj[tankd];
							
							if(nexti>=0 && nexti<H && nextj>=0 && nextj<W && map[nexti][nextj]=='.') {
								tanki = nexti;
								tankj = nextj;
							}
						}
					} //이동 끝
				} 
			} // 명령 끝
			map[tanki][tankj] = arrow[tankd]; //탱크 착륙
			
			System.out.print("#"+tc+" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
}
