package no_rank;

import java.io.*;
import java.util.*;

public class S2115_벌꿀채취 {
	static int N, M, C, map[][], maxMap[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // N : 벌통의 크기
			M = Integer.parseInt(st.nextToken()); // M : 채취할 수 있는 벌통의 수
			C = Integer.parseInt(st.nextToken()); // C : 두 일꾼이 채취할 수 있는 꿀의 최대 양
			maxMap = new int[N][N]; // 꿀을 담을 용기
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println("#" + tc + " " + getMaxBenefit());
		}

	}

	static int getMaxBenefit() {
		makeMaxMap();
		return processCombination();
	}

	private static void makeMaxMap() {
		for (int i = 0; i < N; i++) { // 연속된 m개를 만들 수 있는 위치마다 연속 m개로 만들 수 있는 부분집합의 최대이익 계산
			for (int j = 0; j <= N - M; j++) {
				makeMaxSubset(i, j, 0, 0, 0);
			}
		}

	}

	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {

		if (sum > C)
			return;
		if (cnt == M) {
			if (maxMap[i][j - M] < powSum)
				maxMap[i][j - M] = powSum;
			return;
		}
		// 선택
		makeMaxSubset(i, j + 1, cnt + 1, sum + map[i][j], powSum + map[i][j] * map[i][j]);
		// 비전택
		makeMaxSubset(i, j + 1, cnt + 1, sum, powSum);
	}

	private static int processCombination() {
		int max = 0, aBenefit = 0, bMaxBenefit;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) { // a일꾼
				aBenefit = maxMap[i][j];

				bMaxBenefit = 0;
				// B 일꾼 조합
				for (int j2 = j + M; j2 <= N - M; j2++) { // 같은 행 뒤쪽 열
					if (bMaxBenefit < maxMap[i][j2])
						bMaxBenefit = maxMap[i][j2];
				}

				for (int i2 = i + 1; i2 < N; i2++) { // 다음 행 첫열
					for (int j2 = 0; j2 <= N - M; j2++) {
						if (bMaxBenefit < maxMap[i2][j2])
							bMaxBenefit = maxMap[i2][j2];
					}
				}
				if (aBenefit + bMaxBenefit>max)
					max = aBenefit + bMaxBenefit;
			}
		}
		return max;
	}

}
