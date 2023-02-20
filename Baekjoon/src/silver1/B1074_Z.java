package silver1;

import java.util.Arrays;
import java.util.Scanner;

// 만약 r,c가 오른쪽 아래 있다면 오른쪽 아래만 탐색하면 되지 않은가?
// 오른쪽 아래를 탐색했더니 왼쪽 위에 있다면 왼쪽 위만 탐색하면 되지 않은가?
// r, c의 위치를 찾았다면 그곳에 해당하는 방문횟수를 알아야한다. 
// 각 네모칸의 우하단-좌상단+1은 네모칸의 면적 크기이다. (방문순서 전처리 필요)
public class B1074_Z {
	static int res;
	static int r, c;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();

		int length = (int) Math.pow(2, N); // 한 변 길이
		int half = length/2;
		map = new int[length/2][length/2];
		
		if (0 <= r && r < half && 0 <= c && c < half) {
			concur(0, 0, half);
		} else if (0 <= r && r < half && half <= c && c < N) {
			concur(0, half, half);
		} else if (half <= r && r < N && 0 <= c && c < half) {
			concur(half, 0, half);
		} else if (half <= r && r < N && 0 + half <= c && c < N) {
			concur(half, half, half);
		}
	}
	static void concur(int i, int j, int N) {
		System.out.printf("concur 시작!!: [%d, %d]\n", i, j);
		if (N == 2) {
			for (int[] row : map) {
				System.out.println(Arrays.toString(row));
			}
			res = map[i][j]; // 2줄짜리 배열만 남았을 때 결과를 볼 것임
			System.out.printf("res 시작!! 위치는 [%d, %d]\n", i, j);
			System.out.printf("우리가 가야할 곳은 %d, %d, N: %d\n", r, c, N);
			for (int k = i; k < i + N; k++) {
				System.out.println("i: " + k);
				for (int k2 = j; k2 < j + N; k2++) {
					System.out.println("j: " + k2);
					if (k == r && k2 == c) {
//						System.out.println("k: "+k+"k2: "+k2);
//						System.out.println("r: "+r+"c: "+c);
						System.out.println(res);
						return;
					}
					res++;
				}
			}
			return;
		}
		int half = N / 2;
		int half_size = half * half;

		// 서브네모의 왼쪽 위의 숫자를 지정
		map[i][j + half] = map[i][j] + half_size * 1;
		map[i + half][j] = map[i][j] + half_size * 2;
		map[i + half][j + half] = map[i][j] + half_size * 3;
		// r, c 위치에 따른 방문 시작
		if (i <= r && r < i + half && j <= c && c < j + half) {
//			System.out.println("좌상에 있는듯");
			concur(i, j, half); // 좌상
		} else if (i <= r && r < i + half && j + half <= c && c < j + N) {
//			System.out.println("우상에 있는듯");
			concur(i, j + half, half); // 우상
		} else if (i + half <= r && r < i + N && j <= c && c < j + half) {
//			System.out.println("좌하에 있는듯");
			concur(i + half, j, half); // 좌하
		} else if (i + half <= r && r < i + N && j + half <= c && c < j + N) {
//			System.out.println("우하에 있는듯");
			concur(i + half, j + half, half); // 우하
		}
	}

}
