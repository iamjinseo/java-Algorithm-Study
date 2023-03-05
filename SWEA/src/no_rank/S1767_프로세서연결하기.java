package no_rank;

import java.util.*;
import java.io.*;

/*
 * 매커니즘
 * 한 프로세서의 연결 시작:
 * 	현재 맵 상태를 복사해서 newMap만듬
 * 	지금 프로세서에서 전선 꽂을 수 있는 방향 확인->사방이 0일 때
 * 	전선 꽂는 방향 확인되면 newMap에다가 그 방향으로 계속 2 만들 수 있는지 재귀시작
 * 	계속 가다가 정상적으로 벽 만나면 전선 꽂기 성공
 * 		=>이전 재귀에서 2만들기
 * 		=> 2 다만듬=> 다음 프로세서 꽂기 시작
 * 	프로세서나 전선 만나면 실패
 * 		실패하면 계속 false리턴. 다른 방향 탐색시작
 * 그러다 결국 마지막 프로세서의 전선꽂기가 끝나면 전선개수 세고 최종답안 갱신
 * */
public class S1767_프로세서연결하기 {
	static class Core {
		int i, j;

		public Core(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Core [i=" + i + ", j=" + j + "]";
		}
	}

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int N, maxCore, res, wire;
	static int[][] map;
	static ArrayList<Core> cores = null; // 프로세서 좌표 넣을곳

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();

		// start test case
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new int[N][N];
			cores = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();

					if (map[i][j] == 1)
						cores.add(new Core(i, j));
				}
			} // end input

//			maxCore = 0; // 가장 많이 꽂힌 프로세서 수
			res = Integer.MAX_VALUE; // 가장 많이 프로세서 꽂았을 때의 가장 짧은 전선 길이
			wire = 0; // 전선 수
			plug(0, 0); // 첫번째 플러그 꽂기 시작
			sb.append('#').append(t).append(' ').append(res).append('\n');
		} // end test case
		System.out.println(sb);
	}

	// cnt: 현재 검사중인 코어 수
	// plugedCore: 연결된 코어 수
	private static void plug(int cnt, int plugedCore) {
		// 기저조건: 결국 모든 플러그 다 꽂음
		if (cnt == cores.size()) {
			// 가장 많은 코어가 꽂혔는가?
			System.out.println("다꽂음 " + plugedCore);
			if (plugedCore > maxCore)
				res = Integer.MAX_VALUE; // res값 초기화
			if (plugedCore >= maxCore) {
				maxCore = plugedCore;
				res = Math.min(res, wire);
				System.out.println("결국 전선 길이는 " + res);
			}
			return;
		}

		// 유도파트: 현재 플러그의 네 방향에 꽂을거임 그리고 다음 플러그도 꽂을거임
		int[][] newMap = new int[N][N];
//		copy(newMap, map); // 맵 복사: 현재 단계의 상태 저장
		Core nowCore = cores.get(cnt); // cnt번째 코어 검사

		// 일단 지금 플러그의 네가지 주변부분에 꽂을수 있는지 좀 봐야겠음
		// 근데 벽에 꽂혀잇음: 다음 프로세서 꽂기 ㄱㄱ 그리고 아무방향으로도 안꽂을것
		int top = nowCore.i + di[0];
		int right = nowCore.j + dj[1];
		int down = nowCore.i + di[2];
		int left = nowCore.j + dj[3];

		if (down >= N || top < 0 || right >= N || left < 0) {
			System.out.println(nowCore + " 벽에 꽂혀있음 ");
			System.out.println("다음꺼 꽂으러 간다~~");
			plug(cnt + 1, plugedCore + 1);
			return;
		}

		// 네방향 꽂을 수 있는지 검사
		for (int n = 0; n < 4; n++) {
			int ni = nowCore.i + di[n];
			int nj = nowCore.j + dj[n];
			System.out.println(nowCore + ", ni: " + ni + ", nj: " + nj);

			// 1있음: 못꽂음
			if (map[ni][nj] != 0) {
				plug(cnt + 1, plugedCore);
			}

			// 꽂을 수 있을 거 같음: 벽까지 꽂을수 있는지 검사하고 전선연결
			if (canPlug(ni, nj, n, plugedCore)) {
				System.out.println("꽂을 수 있을 것 같다.");
				// 꽂힘 => 다음프로세서 꽂기 ㄱㄱ

				for (int[] is : map) {
					System.out.println(Arrays.toString(is));
				}
				System.out.println("전선: " + wire);

				System.out.println("이 방향으로 꽂았으니 다음꺼 꽂으러 ㄱㄱ");
				plug(cnt + 1, plugedCore + 1);
				System.out.println("=======전선빼기 시작: " + nowCore);
				reset(ni, nj, n); // 이번 방향 꽂기는 끝났으므로 꽂힌전선 다 뽑음
				System.out.println("전선 뺌: " + wire);
			} else {
				System.out.println("꽂기 실패");
				reset(ni, nj, n);
			}
		}
	}

	// dir 방향으로 전진하며 벽으로 가볼거임
	static boolean canPlug(int i, int j, int dir, int plugedCore) {
		/* 기저조건 */
		// 벽을 만남 : 가능!! 전선 꽂으셈
		if (i < 0 || i >= N || j < 0 || j >= N)
			return true;
		// 벽에 못만났는데 더이상 전진할 수가 없음
		else if (map[i][j] != 0)
			return false;

		// 재귀 실행파트: 다음 방향에 전선을 꽂을 수 있는가?
		if (canPlug(i + di[dir], j + dj[dir], dir, plugedCore)) {
			map[i][j] = 2; // 2는 전선임
			wire++; // 전선늘려
			if (plugedCore + 1 <= maxCore && wire > res) { // 근데 res보다 커지면 안댐
				System.out.print("원래 wird보다 크면 안됨");
				return false;
			}
			return true; // 이전 재귀에도 꽂을 수 있다는 신호 주기
		}
		return false; // 위에서 꽂을 수 있는 신호 못주면 망한거임
	}

	// 전선 꽂은거 되돌리기
	static void reset(int i, int j, int dir) {
		for (int[] is : map) {
			System.out.println(Arrays.toString(is));
		}
		while (i >= 0 && i < N && j >= 0 && j < N && map[i][j] != 1) {
			if (map[i][j] == 2) {
				System.out.printf("[%d %d]에서 전선발견\n", i, j);
				map[i][j] = 0;
				wire--; // 전선줄여
				System.out.println("전선 하나 줄임: " + wire);
			}
			i += di[dir];
			j += dj[dir];
		}
	}

	// 배열 복사
	static void copy(int[][] newMap, int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
}
