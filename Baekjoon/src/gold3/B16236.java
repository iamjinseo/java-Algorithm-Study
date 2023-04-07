package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16236 {
	static class Shark {
		int i, j, time, weight;

		public Shark(int i, int j, int time, int weight) {
			super();
			this.i = i;
			this.j = j;
			this.time = time;
			this.weight = weight;
		}
	}

	static int[][] map;
	static int N, res;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static Shark shark; // 상어의 위치와 상태
	static boolean findFood; // 음식을 찾았는지
	static Queue<Shark> foods = new LinkedList<>();
	static int eatCnt; // 상어가 먹은 물고기 수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j]==9) {
					shark = new Shark(i, j, 0, 2);
					map[i][j] = 0;
				}
			}
		} // end input
		
		do {
			findFood = false;
			System.out.println("=======start bfs=====");
			bfs(shark); // 상어가 이동할 수 있는지 볼 거임
		}while(findFood); // 먹이를 찾을 수 있는 동안
		
		System.out.println(res);
	}
	
	static void bfs(Shark s) {
		boolean[][] visit = new boolean[N][N]; 
		Queue<Shark> q = new LinkedList<>();
		q.offer(s);
		visit[s.i][s.j] = true;
		
		while(!q.isEmpty()) {
			Shark now = q.poll();
			for (int n = 0; n < 4; n++) {
				int ni = now.i + di[n];
				int nj = now.j + dj[n];
				
				if(ni<0 || ni>=N || nj<0 || nj>=N || visit[ni][nj]) continue;
				
				if(map[ni][nj] == 0 || map[ni][nj] == now.weight) { // 이동만 할 수 있음
					System.out.printf("[%d][%d](%d)로 이동. time: %d\n", ni, nj, map[ni][nj], now.time+1);
					q.offer(new Shark(ni, nj, now.time+1, now.weight));
					visit[ni][nj] = true;
				}
				else if(map[ni][nj] < now.weight) { // 먹을 수 있음
					System.out.printf("[%d][%d](%d)로 먹으러감. time: %d\n", ni, nj, map[ni][nj], now.time+1);
					foods.add(new Shark(ni, nj, now.time+1, now.weight));
					
					// 다른 사방에도 먹을 게 있을지도
					for (int n2 = 0; n2 < 4; n2++) {
						
						if(n2==n) continue;
						
						int ni2 = now.i + di[n2];
						int nj2 = now.j + dj[n2];
						
						if(ni2<0 || ni2>=N || nj2<0 || nj2>=N || visit[ni2][nj2]) continue;
						
						if(map[ni2][nj2] < now.weight && map[ni2][nj2]!=0) {
							System.out.printf("[%d][%d](%d)로도 먹으러감ㅋ. time: %d\n", ni2, nj2, map[ni2][nj2], now.time+1);
							foods.add(new Shark(ni2, nj2, now.time+1, now.weight));
						}
					}
					findFood = true;
					break;
				}
			}
		}
		if(!findFood) // 위에서 음식 못찾았으면 망함 
			return; 
		
		// 가까운 음식을 찾았어도 위에있고 왼쪽에 있는 걸 고를 수 있음 
		visit = new boolean[N][N]; 
		int size = q.size();
		while(size-->0) {
			Shark now = q.poll();
			for (int n = 0; n < 4; n++) {
				int ni = now.i + di[n];
				int nj = now.j + dj[n];
				
				if(ni<0 || ni>=N || nj<0 || nj>=N || visit[ni][nj]) continue;
				
				if(map[ni][nj] == 0 || map[ni][nj] == now.weight) { // 이동만 할 수 있음
					q.offer(new Shark(ni, nj, now.time+1, now.weight));
					visit[ni][nj] = true;
				}
				else if(map[ni][nj] < now.weight)  // 먹을 수 있음
					foods.add(new Shark(ni, nj, now.time+1, now.weight));
			}
		}
		
		finalLoc(); // 최종적으로 상어의 상태 변경
	}
	
	// 같은 거리 중 더 위에 있고 왼쪽에 있는 것
	static void finalLoc() {
		Shark loc = foods.poll();
		while(!foods.isEmpty()) {
			Shark s = foods.poll();
			if(s.i < loc.i) { // 위에있는거 선택
				if(s.time <= loc.time ) 
					loc = s;
			}else if(s.i == loc.i) {
				if(s.j < loc.j && s.time <= loc.time ) 
					loc = s;
			}
		}
		
		// 결국 먹이가 골라지고 상어가 그쪽으로 이동
		res = loc.time;
		eatCnt++;
		if(eatCnt == shark.weight) {
			loc.weight++;
			eatCnt=0;
		}
		shark = loc;
		map[loc.i][loc.j] = 0;
		System.out.printf("결국 상어는 [%d][%d]에 있고 지금까지 %d개 먹어서 무게는 %d이고 걸린 시간은 %d임\n", shark.i, shark.j, eatCnt, shark.weight, shark.time);
	}
}
