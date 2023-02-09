package silver2;

import java.util.*;
import java.io.*;

// 가능한 모든 조합을 만들어서 신맛과 쓴맛의 차를 구한다.
// 조합의 경우의 수는 1개만 있는 조합 ~ N개 있는 조합이다
// 조합을 다 구하면 각자 신맛과 쓴맛의 차를 구한다. 
// 조합 함수에서 리턴받았을 때 차가 가장 적은 것을 출력한다. 
public class B2961_도영이가만든맛있는음식 {
	static int N;
	static int[] sour;
	static int[] bitter;
	static ArrayList<Integer> results = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		sour = new int[N];
		bitter = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int s= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			sour[i] =s;
			bitter[i]=b;
		}
		////////////입력 끝 모든 조합 시작///////////////////////////
		for (int i = 1; i <= N; i++) {
			combi(i, 0, 0, 1, 0);
		}  //조합 끝
		////////////가장 작은 차이 구하기/////////////
		int ans = Integer.MAX_VALUE;
		for (Integer i : results) {
			ans = i<ans?i:ans;
		}
		System.out.println(ans);
	}

	// n: 조합할 원소 개수
	// cnt: 조합중인 원소 개수
	// start: 조합하며 넣을 숫자 시작값
	// 조합하며 곱해진 신맛의 곱
	// 조합하며 더해진 쓴맛의 합
	static void combi(int n, int cnt, int start, int sourMultiple, int bitterSum) {
		if(cnt==n) { //n개짜리 조합이 완성되면 
//			System.out.printf("n:%d cnt:%d, sourMultiple:%d, bitterSum:%d\n", n, cnt, sourMultiple, bitterSum);
			results.add(Math.abs(sourMultiple-bitterSum));
		}
		
		for (int i = start; i < N; i++) {
//			sourMultiple *= sour[i];
//			bitterSum += bitter[i];
			combi(n, cnt+1, i+1, sourMultiple*sour[i], bitterSum+bitter[i]);
		}
	}

}
