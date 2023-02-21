package gold5;

import java.util.Scanner;

/*
맵이 0이면 청소 안된거고 1이면 벽임. 벽 만나면 끝
단순한 구현문제로, 문제에서 하라는대로 하면 됨
목표: 로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.
 * */
public class B14503_로봇청소기 {
	static int N, M; //map 세로가로
	static int nowi, nowj, dir; //청소기의 좌표와 방향
	static int[] di = {-1, 0, 1, 0}; //0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽 
	static int[] dj = {0, 1, 0, -1};
	
	static int[][] map;
	static boolean[][] visit; //어떤 칸을 청소했는지 기록해서 또 청소하는건 카운트 안함
	static int ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		visit = new boolean[N][M];
		
		nowi = sc.nextInt(); //로봇청소기 위치
		nowj = sc.nextInt(); //로봇청소기 위치
		dir = sc.nextInt();  //로봇청소기 방향
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		} // end input
		
		while(true) {
			// 1. 현재 칸이 아직 청소x 현재 칸 청소해야댐
			if(!visit[nowi][nowj]) { //로봇청소기가 현재칸 첫방문이면
				visit[nowi][nowj] = true;
				ans++;
			}
			
			// 로봇청소기 주변이 깨끗한지 검사
			boolean clean = true;
			for(int d=0; d<4; d++) {
				int nexti = nowi+di[d];
				int nextj = nowi+dj[d];
				//로봇청소기 주위 4칸중에 청소 안한 빈칸이 있는지?
				if(map[nexti][nextj]==0 && !visit[nexti][nextj])
					clean = false;
			}
			// 2-1. 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 없음
			// 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
			// 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.(완료)
			if(clean) {
				int backi = nowi + di[(dir+2)%4];
				int backj = nowj + dj[(dir+2)%4];
				if(map[backi][backj]==0) { //뒤가 빈칸이라면
					nowi = backi;
					nowj = backj;
				}else {
					break; //가장 가까운 감싸진 반복문이 while(true)임
				}
			}
			
			// 2-2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			// 1. 반시계 방향으로 90도 회전
			// 2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸인 경우 한칸 전진
			// 3. 1번으로 돌아감
			if(!clean) {
				// 90도 회전하려면 현재 방향에서 3번 돌리면 됨.
				dir = (dir+3)%4;
				int nexti = nowi + di[dir];
				int nextj = nowj + dj[dir];
				
				if(map[nexti][nextj]==0 && !visit[nexti][nextj]) {
					nowi = nexti;
					nowj = nextj;
				}
			}
		}
		System.out.println(ans);
	}
}
