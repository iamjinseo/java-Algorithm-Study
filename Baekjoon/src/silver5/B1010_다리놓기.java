package silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1010_다리놓기 {
	static int[][] B;
	static int N, M, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			B = new int[M + 1][N + 1];
			ans = 0;
			
			// mCn
			for(int i = 0; i<= M; i++) {
				for(int j=0, end=Math.min(i, N); j<=end; j++) {
					if(j==0 || i==j) B[i][j]=1;
					else B[i][j] = B[i-1][j-1] + B[i-1][j];
				}
			}
			
			sb.append(B[M][N]).append('\n');
		}
		System.out.println(sb);
	}
}
