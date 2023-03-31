package silver1;

import java.util.Arrays;
import java.util.Scanner;

public class B1149_RGB거리 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N][3];
		int[][] dp = new int[N + 1][3];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 1; i <= N; i++) {
			dp[i][0] = map[i-1][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
			dp[i][1] = map[i-1][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
			dp[i][2] = map[i-1][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
		}
		for (int[] is : dp) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
	}
}
