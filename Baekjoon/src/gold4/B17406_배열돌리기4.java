package gold4;

import java.io.*;
import java.util.*;

/*
입력 N, M, K, map
입력 회전연산
회전 연산 입력 -> 회전 연산 순열 생성
회전 연산 순열 결과에 대한 배열돌리기 실행
	- 회전 연산 시작지점은 (r-s-1, c-s-1) 끝지점은 (r+s-1, c+s-1)
	- 회전 연산 가능 사각형 그룹의 개수는 2s+1/2 => s (2r은 서브배열의 행,열 길이)
	- 배열의 각 사각형 그룹에 대하여 회전연산 
끝났으면 A값 구해서 원래 A값과 비교
 * */
public class B17406_배열돌리기4 {
	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 }; // 시계방향
	static int A = Integer.MAX_VALUE;
	static int N, M, K;
	static int[][] map;
	static int[][] commands; // 회전 연산들
	static int[][] result; // 회전 연산 순열 결과

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		commands = new int[K][3];
		result = new int[K][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end map input

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			commands[k] = new int[] { r, c, s };
		} // end command input

		perm(0, 0); // 회전 연산들에 대한 순열 생성

		System.out.println(A);
	}

	static void perm(int idx, int flag) {
		if (idx == K) { // 순열 생성 완료
			// 각 커맨드에 따른 배열돌리기 실행
			int[][] newMap = Arrays.copyOf(map, N);
			for (int i = 0; i < result.length; i++) {
				System.out.println(i + 1 + "번째: " + Arrays.toString(result[i]));
				newMap = rotate(result[i][0], result[i][1], result[i][2], newMap);
				for (int[] is : newMap) {
					System.out.println(Arrays.toString(is));
				}
			} // 회전 끝
			System.out.println(getA(newMap));
			A = Math.min(A, getA(newMap)); // 회전 완료된 모양에 대한 A구하기
			System.out.println(A);
			return;
		}

		for (int i = 0; i < commands.length; i++) {
			if ((flag & (1 << i)) != 0)
				continue; // command[i]가 쓰인 적 있으면 안됨
			result[idx] = commands[i];
			perm(idx + 1, flag | (1 << i)); // i는 쓰인적이 있다는 신호를 보냄
		}
	}

	static int[][] rotate(int r, int c, int s, int[][] newMap) { // 배열돌리기
		int i, j; // 인덱스 탐색용.
		int insert, next, n; // insert는 넣어야하는 값. next는 넣을 곳이 가지고 있는 값. index는 방향
		for (int g = 0; g < (2*s)+1/2; g++) { // 사각형 그룹 하나당 배열 돌리기
			i = r - s - 1 + g; // 회전 시작 행 위치
			j = c - s - 1 + g; // 열 위치

			insert = newMap[i][j]; // 각 사각형의 시작 지점 저장.
			n = 0; // 스텝용 인덱스
			while (n < 4) { // 4방향으로 돌 것
				int ni = i + di[n];
				int nj = j + dj[n];
				System.out.printf("ni: [%d], nj: [%d]\n", ni, nj);

				if (ni < r - s - 1 + g || ni > r + s - 1 - g || nj < c - s - 1 + g || nj > c + s - 1 - g) { // 범위 벗어났을 때
					n++; // 방향 바꿈
					continue;
				}
				next = newMap[ni][nj]; // 다음 값을 저장
				newMap[ni][nj] = insert; // 넣어야 하는 값을 다음 스텝에 저장
				insert = next; // 저장된 다음 스텝 값은 이제 넣어야 하는 값이 됨
				i = ni;
				j = nj;
			}
		}
		return newMap;
	}

	static int getA(int[][] newMap) { // 모든 행의 합 중의 최솟값
		int sum = Integer.MAX_VALUE;
		for (int[] row : newMap) {
			sum = Math.min(sum, Arrays.stream(row).sum());
		}
		return sum;
	}
}
