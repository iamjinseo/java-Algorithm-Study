package silver1;

import java.util.*;
import java.io.*;

public class B2531_회전초밥 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 수
		int k = Integer.parseInt(st.nextToken()); // 연속 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		int[] sushies = new int[N];

		for (int i = 0; i < N; i++) {
			sushies[i] = Integer.parseInt(br.readLine());
		} // end input

		// start algorithm
		int start = 0;
		int end = 0;
		Set<Integer> set = new HashSet<>();

		// 초기에 포인터 세팅. 0~k-1
		while (end < N) {
			if (end <= k - 1) {
				set.add(sushies[end]);
				System.out.println("start: "+start+", end: " + end + ", " + set);
				end += 1;
				continue;
			}

			set.remove(sushies[start++]);
			set.add(sushies[start]);
			set.add(sushies[end]);
			System.out.println("start: "+start+", end: " + end + ", " + set);
			++end;
		
		}

	}
}
