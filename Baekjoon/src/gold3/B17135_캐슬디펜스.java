package gold3;

import java.io.*;
import java.util.*;

/*
 * 궁수 3명 성에 배치. 각 턴마다 적 하나씩 골라 동시에 공격.
 * 공격하는 적은 거리가 D이하인 적 중에서 궁수에게 가장 가까운 적이고 거리는 |r1-r2| + |c1-c2|
 * 그런 적이 여럿이면 가장 왼쪽에 있는 적 공격. 같은 적 공격 가능.
 * 공격받으면 게임판에서 사라짐. 공격 끝나면 적이 성으로 이동.
 * 성이 있는 칸으로 이동하면 게임에서 제외. 모든 적이 격자판에서 제외되면 게임 끝
 * 격자판의 상태가 주어졌을 때 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해야 한다.
 * */

/*
 * 궁수 3명의 위치를 조합해서 map에 반영
 * 배열 밑에서부터 위로 탐색하며 적이 있는지 검사 -> 궁수 셋으로부터 거리 D이하에 있는지 검사 -> 0으로 만듬
 * 적이 3번 삭제되면 다음 턴으로
 * 게임이 행 수 만큼 진행되면 무조건 모든 적이 아래로 내려와 제외되는 경우이므로 게임 끝
 * */
public class B17135_캐슬디펜스 {
	static int N, M, D;
	static int[][] map;
//	static int[] result; //조합결과
	static ArrayList<int[]> results = new ArrayList<>(); // 조합결과 담을 곳
	static int res = Integer.MAX_VALUE; // 답
	static int enemies = 0; // 적 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		D = Integer.parseInt(st.nextToken()); // 거리제한
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				// 적이면 카운트 (게임 종료 판별용으로 써야함)
				if (map[i][j] == 1)
					enemies++;
			}
		} // end input

		// 궁수 셋 조합하기. 열 위치가 오름차순으로 조합됨
		combi(0, 0, new int[3]);

//// for debugging
//		System.out.println("=====조합결과=====");
//		for (int[] c : results) {
//			System.out.println(Arrays.toString(c));
//		}
//		System.out.println("=====조합결과 끝=====");

		// 궁수 셋 조합에 대한 게임 시작
		for (int[] archers : results) {
//			res = Math.min(res, doGame(archers));
			System.out.println(Arrays.toString(archers) + "들에 대한 게임 시작");
			int gameRes = doGame(archers, Arrays.copyOf(map, map.length));
			res = Math.min(res, gameRes);
			System.out.println("게임 결과: " + gameRes + ", res: " + res);
		}
		System.out.println(res);
	}

	// 궁수 셋을 M개의 열에 세 개 위치할 수 있도록 하는 조합 함수
	// idx는 조합 결과를 위한 인덱스. start는 조합 재료를 위한 인덱스이다.
	// result는 조합 결과이다.
	static void combi(int idx, int start, int[] result) {
		if (idx == 3) { // 궁수 셋 조합 결과 넣기
			int[] temp = Arrays.copyOf(result, result.length);
			results.add(temp);
			return;
		}
		for (int i = start; i < M; i++) {
			result[idx] = i;
			combi(idx + 1, i + 1, result);
		}
	}

	// 궁수 조합 archers에 대한 게임 시작
	static int doGame(int[] archers, int[][] nowMap) {
		int gameRes = 0; // 게임 결과
		int turn = N; // 게임 진행 횟수

		System.out.println("게임 시작. 맵 초기:=====");
		for (int[] row : nowMap) {
			System.out.println(Arrays.toString(row));
		}

		// 게임 턴 시작
		while (turn-- > 0) { // 게임 진행 횟수는 최대 행 개수만큼임
			System.out.println("현재 턴: " + turn);
			int attackCnt=0; //궁수가 적을 없앤 횟수

			// 적이 안남아있으면 게임 끝
			if (allZero(nowMap))
				break;

			// 행 가장 맨 밑에서부터 좌->우로 적 검사
			for (int i = N - 1; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (nowMap[i][j] != 1)
						continue;
					// 현재 위치에는 적이 있으므로 가장 가까운 궁수에게 타격당해야 함
					if(attackCnt==3) break; //그러나 궁수가 세 번 쐈다면 break

					System.out.println("적 발견!!: [" + i + ", " + j + "]");

					for (int archer : archers) {
						// 궁수 각각에 대한 거리 검사
						int distance = Math.abs(i - N) + Math.abs(j - archer);

						System.out.println(archer + "와의 거리는: " + distance);

						if (distance <= D) {
							nowMap[i][j] = 0; // 타격당해서 삭제
							gameRes++;   //맞은 적 증가
							attackCnt++; //궁수가 때린 횟수 증가
							System.out.println("타격당했습니다. 카운트: " + gameRes);
							break;
						}
					}
				}
				if(attackCnt==3) break;
			} // 적 공격 끝

			// 적이 전부 제거되면 게임 끝
			if (gameRes == enemies)
				break;

			// 적들이 성으로 전진
			nowMap = goEnemy(nowMap);
			System.out.println("적들이 성으로 전진했습니다");
			for (int[] row : nowMap) {
				System.out.println(Arrays.toString(row));
			}
			System.out.println("=====다음 턴으로 이동합니다======");
		}
		return gameRes;
	}

	// 적들을 아래로 내리는 함수
	static int[][] goEnemy(int[][] nowMap) {
		int[][] newMap = new int[N][M];

		// 밑에서부터 한 행씩 탐색하며 위의 행을 아래로 내림
		for (int i = N - 1; i > 0; i--) {
			newMap[i] = nowMap[i - 1];
		} // 0번 행은 모두 0이어야 하기에 마지막 행을 삽입하지 않음.
		return newMap;
	}

	static boolean allZero(int[][] nowMap) {
		int sum = 0;
		for (int[] row : nowMap) {
			for (int i : row) {
				sum += i;
			}
		}
		if(sum ==0)
			return true;
		else
			return false;
	}
}
