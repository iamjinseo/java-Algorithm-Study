package gold4;

import java.util.*;
import java.io.*;

public class B1504_특정한최단경로 {
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 노드 수
		int E = Integer.parseInt(st.nextToken()); // 간선 수
		int[][] adjMatrix = new int[N+1][N+1];
		for (int[] row : adjMatrix) {
			Arrays.fill(row, INF);
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjMatrix[from][to] = adjMatrix[to][from] = weight;
		}

		st = new StringTokenizer(br.readLine());
		int via1 = Integer.parseInt(st.nextToken()); // 경유 노드1
		int via2 = Integer.parseInt(st.nextToken()); // 경유 노드2
		// end input

		// start floyd-warshall
		for (int k = 1; k <= N; k++) { // k를 경유하여
			for (int i = 1; i <= N; i++) { // i에서
				for (int j = 1; j <= N; j++) { // j으로
					if(i==j) {
						adjMatrix[i][j] = adjMatrix[j][i] = 0;
						continue;
					}
					// 경유해서 갈 수 있고 그게 더 빠르면 최단경로 갱신
					if(adjMatrix[i][k]!=INF && adjMatrix[k][j]!=INF
					&& adjMatrix[i][j] > adjMatrix[i][k]+adjMatrix[k][j])
						adjMatrix[i][j] = adjMatrix[i][k]+adjMatrix[k][j];
				}
			}
		}

		// 최단경로를 구할 때 어느 쪽이라도 갈 수 없는 길이 있으면 안됨
		if(adjMatrix[1][via1]==INF || adjMatrix[1][via2] == INF ||
			adjMatrix[via1][via2]==INF || adjMatrix[via2][via1] == INF ||
			adjMatrix[via1][N]==INF || adjMatrix[via2][N]==INF) {
			System.out.println(-1);
			System.exit(0);
		}
		
		// 1~via1 최단경로, via1~via2 최단경로, via2 ~ N 최단경로 
		int minWay1 = adjMatrix[1][via1]+adjMatrix[via1][via2]+adjMatrix[via2][N];
		// 1~via2 최단경로, via2~via1 최단경로, via1 ~ N 최단경로 
		int minWay2 = adjMatrix[1][via2]+adjMatrix[via2][via1]+adjMatrix[via1][N];
		
		System.out.printf("%d", Math.min(minWay1, minWay2));
	}
}
