package gold3;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B1941_소문난칠공주 {
	static class Pair {
		int i, j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

	}

	static char[][] map = new char[5][5];
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static int res;
	static boolean[] used;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		used = new boolean[25];
		for (int i = 0; i < 5; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		combi(0, 0);
		System.out.println(res);
	}

	static void combi(int idx, int cnt) {
		if(cnt==7) { // 아무거나 7칸 뽑음
			// 1. 7명 다 뿥어있는지 체크
			boolean isSeven = false;
			for (int i = 0; i < 25; i++) {
				if(used[i]) {
					isSeven = bfs(i);
					break;
				}
			}
			
			// 2. S가 4개 이상인지 체크
			int scnt = 0; 
			for(int i=0; i<25; i++) {
				if(used[i] && map[i/5][i%5]=='S')
					scnt++;
			}
			
			if(isSeven && scnt>=4) // 뽑은 7개가 모두 붙어있고 S가 4개 이상이면 경우의수 카운트
				res++;
			
			return; 
		}
		if(idx == 25) return;
		
		used[idx] = true;
		combi(idx + 1, cnt + 1);
		used[idx] = false;
		combi(idx + 1, cnt);
	}
	
	static boolean bfs(int start) {
		int si = start/5;
		int sj = start%5;
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visit = new boolean[5][5];
		
		q.offer(new Pair(si, sj));
		visit[si][sj] = true;
		
		int cnt=0;
		while(!q.isEmpty()) {
			Pair now = q.poll();
			cnt++; // 하나 꺼낼 때마다 카운트+1
			
			if(cnt==7) return true; // 칠공주가 싹다 붙어있음
			
			for (int n = 0; n < 4; n++) {
				int ni = now.i + di[n];
				int nj = now.j + dj[n];

				// 상하좌우에 칠공주가 붙어있으면
				if(ni>=0 && ni<5 && nj>=0 && nj<5 && used[ni*5+nj] && !visit[ni][nj]) {
					q.offer(new Pair(ni, nj));
					visit[ni][nj] = true;
				}
			}
		}
		return false;
	}
}
