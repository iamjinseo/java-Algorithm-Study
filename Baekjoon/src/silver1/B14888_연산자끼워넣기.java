package silver1;

import java.util.Arrays;
import java.util.Scanner;

// 순열 이용
public class B14888_연산자끼워넣기 {
	static char[] cards; //연산자들. 순열 재료
	static char[] result;  //순열 결과
	static boolean[] used; //이미 사용중인 재료
	static int ansMax, ansMin, N; //최소, 최대, 숫자개수
	static int[] numbers; //숫자들
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); //숫자가 N개다
		numbers=new int[N];  //N개의 숫자 넣을 곳
		cards = new char[N-1]; //연산자는 N개보다 1개 적어야함
		result = new char[N-1]; //연산자 순열 결과
		used = new boolean[N-1]; 
		ansMax = Integer.MIN_VALUE;
		ansMin = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			numbers[i] = sc.nextInt();
		} // 숫자들 입력 완료
		
		int cardIdx = 0;
		for (int i = 0; i < 4; i++) { //i:지금 연산자
			int count = sc.nextInt();   //지금 연산자의 개수
			for (int j = 0; j < count; j++) { //연산자 개수만큼  cards에 넣어야함
				switch (i) {  // 지금 연산자에 따른 스위치문
				case 0:
					cards[cardIdx++] = '+'; break;
				case 1:
					cards[cardIdx++] = '-'; break;
				case 2:
					cards[cardIdx++] = '*'; break;
				case 3:
					cards[cardIdx++] = '/'; break;
				}
			}
		}
		perm(0);
		System.out.println(ansMax);
		System.out.println(ansMin);
	}
	
	static void perm(int idx) {
		if(idx == result.length) { //연산자 순열 완성
			int tmp = 0;
			tmp += numbers[0]; //일단 첫번째 숫자 하나 넣기
			for (int i = 0; i < result.length; i++) { //모든 연산자 꺼내 계산
				int next = numbers[i+1]; //연산자 하나 뒤에 있는 숫자
				switch (result[i]) {
				case '+': tmp += next; break;
				case '-': tmp -= next; break;
				case '*': tmp *= next; break;
				case '/': tmp /= next; break;
				}
			}
			ansMax = Math.max(tmp, ansMax);
			ansMin = Math.min(tmp, ansMin);
			
			return;
		}
		
		for (int i = 0; i < cards.length; i++) {
			if(used[i]) continue;
			result[idx] = cards[i];
			used[i] = true;
			perm(idx+1);
			used[i] = false;
		}
	}
}
