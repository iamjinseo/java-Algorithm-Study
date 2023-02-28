package D4;

import java.io.*;
import java.util.*;

/* 서로소 집합 (유니온파인드) 문제
 * 배열로 서로소 집합을 구현해내면 됨
 * */
public class S7465_창용마을무리의개수 {
	static int[] disjoint;
	static int N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 사람수
			makeSet(); // 서로소집합 생성
			M = Integer.parseInt(st.nextToken());
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
//			System.out.println(Arrays.toString(disjoint));
			int res = 0;
			for (int i = 1; i <= N; i++) {
				if(i==disjoint[i])
					res++;
			}
			System.out.printf("#%d %d\n", t, res);
		}
	}

	static void makeSet() {
		disjoint = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			disjoint[i] = i;
		}
	}

	static int find(int i) {
		if (i == disjoint[i])
			return i;
		else
			return disjoint[i] = find(disjoint[i]);
	}

	static boolean union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);

		if (p1 == p2) // 알고보니 둘이 같은소속!!
			return false; // 그럼 안됨
		disjoint[p1] = p2;
		return true;
	}
}
