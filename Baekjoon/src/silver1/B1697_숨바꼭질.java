package silver1;

import java.util.*;

public class B1697_숨바꼭질 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int start = sc.nextInt();
		int goal = sc.nextInt();
		int[] dp = new int[100001];
		Arrays.fill(dp, Integer.MAX_VALUE-1); //-1은 overflow떄문에 넣음..

		//목표 <= 출발
		if (goal <= start) {
			System.out.println(start - goal);
			return;
		} 
		else { 	// 목표 > 출발
			dp[start] = 0;
			dp[start*2] = 1;
//			System.out.println("dp: "+Arrays.toString(dp));
			
			for (int n = start+1; n < dp.length; n++) {
				// dp[n] vs dp[n-1]+1 vs dp[n+1]+1
				dp[n] = Math.min(dp[n], Math.min(dp[n-1]+1, n-start));
				
				// dp[n+1]+1할 때는 범위초과 날 수 있음
				if(n+1!=dp.length)
					dp[n] = Math.min(dp[n], dp[n+1]+1);
				
				// dp[2n] = dp[n]+1
				if(n <= (dp.length-1)/2) 
					dp[2*n] = dp[n]+1;
				
//				System.out.println("dp: "+Arrays.toString(dp));
			}
		}
		System.out.println(dp[goal]);
	}
}
