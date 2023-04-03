package D6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1263_사람네트워크2 {
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int dist[][];
		int ans;

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Integer.parseInt(st.nextToken());
					if (dist[i][j] == 0)
						dist[i][j] = INF;
				}
			} // end input //

			// print
			System.out.println("========플로이드 워셜 전========");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(dist[i][j] + " ");
				}
				System.out.println();
			}

			// Floyd-Warshall
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (i == k)
						continue;
					for (int j = 0; j < N; j++) {
						if (i == j || j == k)
							continue;
						if (dist[i][k] != INF && dist[k][j] != INF && dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
					}
				}
			}

			// print
			System.out.println("========플로이드 워셜 후========");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(dist[i][j] + " ");
				}
				System.out.println();
			}

			// dist의 한 행의 합은 그 행의 CC
			ans = 0;
			int res = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue; // 자기 자신을 연결하지 않음
					sum += dist[i][j];
				}
				if (sum < res) {
					ans = i;
					res = sum;
				}
			}

			sb.append("#").append(t).append(" ").append(res).append('\n');
		} // end testcase
		System.out.println(sb);
	}

}
