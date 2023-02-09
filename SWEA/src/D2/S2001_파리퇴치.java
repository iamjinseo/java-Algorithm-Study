package D2;

import java.util.*;
import java.io.*;

public class S2001_파리퇴치 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = 0;
			for (int i = 0; i < N-M+1; i++) {
				for (int j = 0; j < N-M+1; j++) {
					int sum = 0;
					for (int k = i; k < i + M; k++) {
						for (int k2 = j; k2 < j + M; k2++) {
							sum += map[k][k2];
						}
					}
					max = sum > max ? sum : max;
				}
			}
			System.out.printf("#%d %d\n", t, max);
		}
	}
}
