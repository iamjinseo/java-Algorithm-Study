package silver1;

import java.util.*;
import java.io.*;

public class B11403_경로찾기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] adjMatrix = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		for (int k = 1; k <=N; k++) {
			for (int i = 1; i <=N; i++) {
				for (int j = 1; j <=N; j++) {
					if(adjMatrix[i][k]>0 && adjMatrix[k][j]>0)
						adjMatrix[i][j] = 1;
				}
			}
		}

		// print adjMatrix
		for (int i = 1; i <=N; i++) {
			for (int j = 1; j <= N; j++) {
				if (adjMatrix[i][j] == 0) {
					System.out.print("0 ");
				} else {
					System.out.print(adjMatrix[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
