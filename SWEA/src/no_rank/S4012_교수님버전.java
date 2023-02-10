package no_rank;

import java.util.Scanner;

public class S4012_교수님버전 {
	static int[][] arr; //재료에 대한 점수들
	static int ans, N;  //두 요리의 점수차이 최소값
	static boolean[] select; //N/2개재료에는 true 나머지는 false해서 각각 두가지 음식
	//
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			arr = new int[N][N];
			select = new boolean[N];
			ans = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			comb(0,0);
			System.out.println("#"+t+" "+ans);
		}
	}
	static void comb(int idx, int cnt) { //부분집합으로 N/2개의 조합 만들기
		if(cnt == N/2) { //조합 생성 완료
			int sumA=0, sumB=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(select[i] && select[j]) { // 밖에 i재료가 true인 상태에서 안에 j=0,1,2보면서 true인 애들만 시너지 누적
						sumA += arr[i][j];
					}else if (!select[i] && !select[j]){ // 밖에 i재료가 false인 상태에서 안에 j=0,1,2보면서 false인 애들만 시너지 누적
						sumB += arr[i][j];
					}
				}
			}
			ans = Math.min(ans, Math.abs(sumA-sumB));
			return;
		}
		
		if(idx==N) return;
		
		select[idx] = true;  //현재 재료를 쓰겠다
		comb(idx+1, cnt+1);  //다음 재료를 쓸 것인가
		select[idx] = false; //현재 재료를 안쓰겠다
		comb(idx+1, cnt+1);  //다음 재료를 쓸 것인가
	}
}
