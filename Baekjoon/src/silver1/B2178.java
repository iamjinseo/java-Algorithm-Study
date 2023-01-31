package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B2178 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) { // map 입력
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(Character.toString(str.charAt(j)));
			}
		} // map 입력 끝

		// map탐색 시작
		int[] dr = { 1, -1, 0, 0 }; 			 // 인접블록탐색용
		int[] dc = { 0, 0, 1, -1 }; 			 // 인접블록탐색용
		int ans = 0; 							 // 정답 출력용
		Queue<int[]> q = new LinkedList<>(); 	 // 탐색용 queue. BFS
		boolean[][] visited = new boolean[N][M]; // 탐색했는지 검사

		int[] startPoint = { 0, 0, 1 }; //탐색 시작지점과 건넌 블록수
		q.add(startPoint);				//시작지점 큐에 넣고 시작
		
		/*탐색 찐 시작*/
		while (!q.isEmpty()) { 			
			int[] coor = q.poll(); // 좌표와 탐색을 몇번했는지 들어있는 변수
			int r = coor[0]; 	   // 현재 행 좌표
			int c = coor[1]; 	   // 현재 열 좌표
			int block = coor[2];   // 현재 블록 탐색 개수
			
			if (r == N - 1 && c == M-1) { //N, M에 도달했는지 검사
				ans = block; //도달했으니 정답은 현재 블록 탐색 개수
//				//for debugging
//				System.out.println("N, M도달!!: "+r+" "+c);		
				break;
			}
//			//for debugging
//			System.out.println("======현재 r: "+r+" 현재 c: "+c+" 현재 block: "+block+"======");
			for (int n = 0; n < 4; n++) {
				int nr = r + dr[n]; // 다음 행 위치
				int nc = c + dc[n]; // 다음 열 위치
				if (0 <= nr && nr < N && 0 <= nc && nc < M) { // 다음 블록이 범위 내
					if (map[nr][nc]==1 && !visited[nr][nc]) { // 다음이 1이고 탐색 안했는지 검사
						visited[nr][nc] = true;
						int[] nowPoint = { nr, nc, block+1 };
						q.add(nowPoint);
//						//for debugging
//						System.out.println("nr: "+nr+" nc: "+nc);		
//						//for debugging
//						System.out.println("블록 찾음!");
//						System.out.println(Arrays.toString(nowPoint));	
					} // 1 및 탐색 여부 검사 끝
				} // 범위 검사 끝			
			} // 인접 블록 검사 끝
		} // 탐색 끝
		System.out.println(ans);
	}
}
