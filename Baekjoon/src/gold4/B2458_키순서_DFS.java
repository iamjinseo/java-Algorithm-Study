//package gold4;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class B2458_키순서_DFS {
//	static int N, M, adj[][];
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		int TC = Integer.parseInt(in.readLine());
//		
//		for (int tc = 0; tc < TC; tc++) {
//			N = Integer.parseInt(in.readLine());
//			M = Integer.parseInt(in.readLine());
//			
//			adj = new int[N+1][N+1]; // 자신보다 키가 큰 관계
//			
//			StringTokenizer st = null;
//			int a, b;
//			for (int m = 0; m < M; m++) {
//				st = new StringTokenizer(in.readLine());
//				a = Integer.parseInt(st.nextToken());
//				b = Integer.parseInt(st.nextToken());
//				adj[a][b] = 1; // 유향
//			}
//		}
//	}
//	static void gtDFS(int cur, boolean[] visited) {
//		// cur 정점 기준으로 자신보다 큰 정점 탐색
//		for (int i = 1; i <= N; i++) {
//			if(adj[cur][i])
//		}
//	}
//}
