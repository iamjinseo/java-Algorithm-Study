package silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11659_구간합구하기4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 첫째 줄 N, M입력
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()); // N개의 수
		int nums[] = new int[N+1];
		for (int i = 1; i < nums.length; i++) { // 누적합 구하기
			nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		// 구간합 구하기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			System.out.println(nums[to] - nums[from - 1]);
		}
	}
}
