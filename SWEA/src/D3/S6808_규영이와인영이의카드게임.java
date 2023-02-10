package D3;

import java.util.Arrays;
import java.util.Scanner;

public class S6808_규영이와인영이의카드게임 {
	static boolean[] wholeCards; // 규영이 인영이가 쓰는 모든 카드
	static int[] inCards, kyuCards; // 인영이에게 주어진 카드(순열 재료)

	static int[] result; // 인영이가 낼 카드 순서(순열 결과)
	static boolean[] used; // 순열 재귀들 사이에 앞선 재귀가 사용중인 카드를 기록해놓은 배열

	static int kyuWin, inWin; // 규영이, 인영이 승리

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			wholeCards = new boolean[19]; // 1번부터 18번까지 카드 인덱스
			kyuCards = new int[9];
			inCards = new int[9];
			result = new int[9];
			used = new boolean[9];
			kyuWin = inWin = 0;

			int tmp = 0;
			// 규영이에게 주어진 카드 입력
			for (int i = 0; i < 9; i++) {
				kyuCards[i] = sc.nextInt();
				wholeCards[kyuCards[i]] = true; // 사용중
			} // end input

			int idx = 0;
			// 인영이에게 주어진 카드 입력
			for (int i = 1; i <= 18; i++) { // 1~18번 전체 카드를 보면서
				if (!wholeCards[i]) { // 규영이가 미사용중인 카드번호 i를 찾아
					inCards[idx] = i; // 인영이 순열 재료
					idx++;
				}
			} // 순열 재료 확보 끝

//			System.out.println(Arrays.toString(kyuCards));
//			System.out.println(Arrays.toString(inCards));

			perm(0);
			System.out.printf("#%d %d %d\n", t, kyuWin, inWin);
		}
	}

	static void perm(int idx) {
		//result 배열의 모든 칸이 채워졌나? idx가 0~8까지 다 채워졌는지 확인
		if(idx==inCards.length) { 
			int kyuSum =0, inSum=0; //각자의 카드 총합
			
			//인영이 카드 순열을 다 만들고나서 각자의 카드를 순회한다.
			for (int i = 0; i < result.length; i++) { //라운드 하나씩 
				if(kyuCards[i] < result[i]) { //이번 라운드에서 인영이 카드가 더 크면
					inSum += kyuCards[i] + result[i]; //인영이의 점수
				}else { //규영이 카드가 더 크면
					kyuSum += kyuCards[i] + result[i]; //규영이 점수
				}
			}
			if(kyuSum < inSum) inWin++;
			else if(inSum < kyuSum) kyuWin++;
			
			return;
		}
		
		for (int i = 0; i < inCards.length; i++) { //인영이가 갖고있는 카드들
			if(used[i]) continue; //사용중인 카드는 안씀
			result[idx] = inCards[i]; 
			used[i] = true;
			perm(idx+1);
			used[i] = false;
		}
	}
}
