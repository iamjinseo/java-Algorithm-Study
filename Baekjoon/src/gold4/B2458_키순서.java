package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 나보다 위에있는 노드 + 아래에 있는 노드가 N-1이면 등수 알 수 있음
 * -------------------------------------
 * 다른 노드를 경유해서라도 어떤 노드로 갈 수 있는지 검사 -> 플로이드 워셜
 * 인접행렬에서 N이 행일 때 1인 수 + N이 열일 때 1인 수가 N이면 res+1
 * */
public class B2458_키순서 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int res = 0;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 인접행렬 기본적으로 false으로 초기화 => 만날 수 없다는 뜻
		boolean[][] adjMatrix = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = true;
		} // end input. end create adjacent matrix

//		for (boolean[] bs : adjMatrix) {
//			System.out.println(Arrays.toString(bs));
//		}

		// Floyd-Warshall: i노드에서 다른 노드를 경유해서라도 j로 갈 수 있는지 검사 
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k) continue;
				for (int j = 1; j <= N; j++) {
					if (i == j || j == k) continue;
					// i 에서 j로 갈 수 있음 
					if(adjMatrix[i][k] && adjMatrix[k][j]) {
						adjMatrix[i][j] = true;
					}
				}
			}
		} // end Floyd
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(adjMatrix[i][j]+" ");
			}
			System.out.println();
		}
		
		// 인접행렬에서, 어떤 수 n이 행일 때 true인 수 + 어떤 수 n이 열일 때 true인 수 = N-1이면 가능
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			// i가 행일 때
			for (int j = 1; j <= N; j++) {
				if(i==j) continue;
				if(adjMatrix[i][j]) cnt++;
			}
			for (int j = 1; j <= N; j++) {
				if(i==j) continue;
				if(adjMatrix[j][i]) cnt++;
			}
			if(cnt==N-1) res++;
		}
		System.out.println(res);
	}
}
