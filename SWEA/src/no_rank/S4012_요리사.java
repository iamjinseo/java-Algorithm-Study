package no_rank;

import java.io.*;
import java.util.*;

// 식재료가 1234 인 경우에 2자리 조합의 식재료 만들면 나머지 두개는 다른 식재료임
// 식재료 반반씩 구했으면 시너지 구함
// 예를 들어 12, 34로 나뉘었으면 12의 시너지+21의 시너지 - 34의 시너지+43의 시너지
public class S4012_요리사 {
	static int N;
	static boolean[] select;
	static ArrayList<boolean[]> combination = new ArrayList<>(); // 조합결과 배열

	public static void main(String[] args) throws IOException {
		// 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		// 테스트케이스 시작
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // N입력

			select = new boolean[N]; // 조합 결과 배열
			combi(0);
//			for (boolean[] s : combination) {
//				System.out.println(Arrays.toString(s));
//			}

//			int[][] map = new int[N][N];
//			
//			for (int i = 0; i < N; i++) {
//				StringTokenizer st = new StringTokenizer(br.readLine());
//				for (int j = 0; j < N; j++) {
//					map[i][j] = Integer.parseInt(st.nextToken());
//				}
//			}// 맵 입력 끝

		} // 테스트케이스 끝
	}

	static void combi(int idx) {
		if (idx == N) {
			int trueNum = 0;
			for (boolean bs : select) {
				if (bs == true)
					trueNum++;
			}
			if (trueNum == 2) {
				System.out.println("=============");
				System.out.println(Arrays.toString(select));
				combination.add(Arrays.copyOf(select));
				for (boolean[] s : combination) {
					System.out.println(Arrays.toString(s));
				}
				System.out.println("=============");
			}
			return;
		}
		select[idx] = true;
		combi(idx + 1);
		select[idx] = false;
		combi(idx + 1);
	}
}
