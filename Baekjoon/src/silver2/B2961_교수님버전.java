package silver2;

import java.util.Scanner;

public class B2961_교수님버전 {
	static Ingredient[] cards; //부분집합을 만들 재료(모집합)
	static int ans, N; //정답 및 재료 개수
	static boolean[] select; //부분집합
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		cards = new Ingredient[N];
		select = new boolean[N];
		
		ans = Integer.MAX_VALUE;
		
		for (int n = 0; n < N; n++) {
			cards[n] = new Ingredient(sc.nextInt(), sc.nextInt()); //신맛, 쓴맛
		} //end input
		
		subSet(0);
		System.out.println(ans);
	}
	static void subSet(int idx) {
		if(idx==N) { //N개의 재료에 true, false가 기록 됨
			int sourSum = 1, bitterSum  =0;
			for (int i = 0; i < N; i++) { //신맛 곱, 쓴맛 합 구하기
				if(select[i]) { // 선택된 재료일 때
					sourSum *= cards[i].sour; //맛 중에서 신맛의 곱을 구함
					bitterSum *= cards[i].bitter; // 쓴맛의 합을 구함
				}
			}
			ans = Math.min(ans, Math.abs(sourSum - bitterSum));
			return;
		}
		
		select[idx]=true;
		subSet(idx+1);
		select[idx]=false;
		subSet(idx+1);
	}
	
	static class Ingredient{
		int bitter, sour;

		public Ingredient(int bitter, int sour) {
			this.bitter = bitter;
			this.sour = sour;
		} 
	}
}
