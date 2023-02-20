//package gold4;
//
//import java.util.Scanner;
//
//public class B17406_배열돌리기4_교수님버전 {
//	static int N,M,K;
//	static int[][] map;
//	static Rotate[] cards; 
//	static Rotate[] result;
//	static boolean[] used;
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		N = sc.nextInt();
//		M = sc.nextInt();
//		K = sc.nextInt();
//		
//		map = new int[N+1][M+1];
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= M; j++) {
//				map[i][j] = sc.nextInt();
//			}
//		}
//		
//		for (int k = 0; k < K; k++) {
//			cards[k] = new Rotate(sc.nextInt(), sc.nextInt(), sc.nextInt());
//		}
////		int[][] copy = deepcopy(map);
////		rotate(copy, cards[0]);
////		print(copy);
//		
//		perm(0);
//	}
//
//	static int[][] deepcopy(int[][] origin) {
//		int[][] copy = new int[origin.length][origin[0].length];
//		for (int i = 0; i < origin.length; i++) {
//			for (int j = 0; j < origin[0].length; j++) {
//				copy[i][j] = origin[i][j];
//			}
//		}
//		return copy;
//	}
//
//	static void rotate(int[][] copy, Rotate now) {
//		for (int s = 1; s <= now.s; s++) {
//			int i = now.r - s, j = now.c - s;
//
//			int tmp = copy[i][j]; // 현재 회전하는 구간 왼쪽 위 한칸
//
//			for (i = now.r - s; i < now.r + s; i++) {
//				copy[i][j] = copy[i + 1][j];
//			}
//			for (j = now.c - s; j < now.c + s; j++) {
//				copy[i][j] = copy[i][j + 1];
//			}
//			for (i = now.r + s; i > now.r - s; i--) {
//				copy[i][j] = copy[i - 1][j];
//			}
//			for (j = now.c + s; j > now.c - s; j--) {
//				copy[i][j] = copy[i][j - 1];
//			}
//			copy[now.r - s][now.c - s + 1] = tmp;
//		}
//	}
//
//	static class Rotate {
//		int r, c, s;
//
//		public Rotate(int r, int c, int s) {
//			this.r = r;
//			this.c = c;
//			this.s = s;
//		}
//
//		@Override
//		public String toString() {
//			return "Rotate [r=" + r + ", c=" + c + ", s=" + s + "]";
//		}
//	}
//	
//	static void perm(int idx) {
//		if(idx == result.length) {
//			int[][] copy = deepcopy(map);
//			for (int i = 0; i < result.length; i++) {
//				rotate(copy, result[i]);
//			}
//			
//			for (int i = 0; i <= N; i++) {
//				int rowSum = 0;
//				for (int j = 1; j <= M; j++) {
//					rowSum += copy[i][j];
//				}
//				ans = Math.min(a, b);
//			}
//			return;
//		}
//		
//		for (int i = 0; i < cards.length; i++) {
//			if(used[i]) continue;
//			
//			result[idx] = cards[i];
//			used[i] = true;
//			perm(idx+1);
//			used[i] = false;
//		}
//	}
//}
