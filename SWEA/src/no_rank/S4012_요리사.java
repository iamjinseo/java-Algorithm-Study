package no_rank;

import java.io.*;
import java.util.*;

// 식재료가 1234 인 경우에 2자리 조합의 식재료 만들면 나머지 두개는 다른 식재료임
// 식재료 반반씩 구했으면 시너지 구함
// 예를 들어 12, 34로 나뉘었으면 12의 시너지+21의 시너지 - 34의 시너지+43의 시너지
public class S4012_요리사 {
	static int N;
	static int[][] map;
	static boolean[] select;
	static ArrayList<boolean[]> combination; // N/2조합결과 배열

	public static void main(String[] args) throws IOException {
		// 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		// 테스트케이스 시작
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // N입력

			select = new boolean[N]; // 조합 결과 배열
			combination = new ArrayList<boolean[]>();
			combi(0); // true와 false가 각각 N/2개로 나뉘는 부분집합

			map = new int[N][N];

			for (int i = 0; i < N; i++) { //맵 입력 시작
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 맵 입력 끝

			int min = Integer.MAX_VALUE; // 가장 낮은 맛의 차이

			for (boolean[] bs : combination) { // 모든 조합에 대해서
				ArrayList<Integer> trueTeam = new ArrayList<Integer>(); // 조합에서 true나온 애들끼리
				ArrayList<Integer> falseTeam = new ArrayList<Integer>(); // false 나온 애들끼리

				for (int i = 0; i < bs.length; i++) { // 현재 조합 검사
					if (bs[i]) // 조합에서 true가 나옴
						trueTeam.add(i);
					else
						falseTeam.add(i);
				} // 현재 조합 검사하여 구성에 더하기 끝
				int synergy1 = getSynergy(trueTeam); // 조합에서 true나온 애들끼리의 시너지
				int synergy2 = getSynergy(falseTeam); // false나온 애들끼리의 시너지
//				System.out.printf("synergy1 2: %d %d\n", synergy1, synergy2);
				int res = Math.abs(synergy1 - synergy2); // 맛의 차이
				min = res < min ? res : min;
			} // 모든 조합 검사 끝
			System.out.printf("#%d %d\n",t, min);
		} // 테스트케이스 끝
	}

	static void combi(int idx) {
		if (idx == N) {
			int trueNum = 0;
			for (boolean bs : select) {
				if (bs == true)
					trueNum++; //true가 N의 반개인 조합을 찾는 부분
			}
			if (trueNum == N / 2) {
				boolean[] arr = Arrays.copyOf(select, select.length);
//				System.out.println(Arrays.toString(select));
				combination.add(arr);
			}
			return;
		}
		select[idx] = true;
		combi(idx + 1);

		select[idx] = false;
		// true, true, false, false와 false, false, true, true는 어쨌든 같은 조합
		if (select[0] == false) {
			return;
		}
		combi(idx + 1);
	}

	// N/2개의 재료들 중에서 또 2가지 조합을 구해 그 둘의 시너지 합을 구하는 함수
	static int getSynergy(ArrayList<Integer> team) {
		// team: trueTeam or falseTeam
		int sum = 0;
		for (int i = 0; i < team.size(); i++) {
			for (int j = i+1; j < team.size(); j++) {
				int a = team.get(i);
				int b = team.get(j);
				sum += map[a][b] + map[b][a];
			}
		}
		return sum;
	}
}
